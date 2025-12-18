package com.CompanyName.ConnectingMultipleDB1.UserDatabase.Configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.sql.DataSource;

//MySQL Database
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.CompanyName.ConnectingMultipleDB1.UserDatabase.Repository"},
        entityManagerFactoryRef = "userEntityManagerFactory",
        transactionManagerRef = "userTransactionManager"
)
public class UserDatabaseConfig {

    @Primary
    @Bean(name = "userDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.user")
    public DataSource userDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "userEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(){
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(userDataSource());
        entityManagerFactory.setPackagesToScan("com.CompanyName.ConnectingMultipleDB1.UserDataBase.Model");
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        //Additional JPA properties
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("spring.jpa.show-sql", "true");

        entityManagerFactory.setJpaPropertyMap(properties);

        return entityManagerFactory;
    }

    @Primary
    @Bean(name = "userTransactionManager")
    public PlatformTransactionManager userTransactionManager(
            @Qualifier("userEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory
    ){
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }

}
