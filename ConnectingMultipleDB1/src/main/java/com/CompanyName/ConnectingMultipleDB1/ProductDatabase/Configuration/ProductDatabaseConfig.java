package com.CompanyName.ConnectingMultipleDB1.ProductDatabase.Configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

//Oracle Database
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.CompanyName.ConnectingMultipleDB1.ProductDatabase.Repository",
        entityManagerFactoryRef = "productEntityManagerFactory",
        transactionManagerRef = "productTransactionManager"
)
public class ProductDatabaseConfig {

    @Bean(name = "productDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.product")
    public DataSource productDatasource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "productEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(productDatasource());
        entityManagerFactory.setPackagesToScan("com.CompanyName.ConnectingMultipleDB1.ProductDatabase.Model");
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect","org.hibernate.dialect.OracleDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("spring.jpa.show-sql", "true");

        entityManagerFactory.setJpaPropertyMap(properties);

        return entityManagerFactory;
    }

    @Bean(name = "productTransactionManager")
    public PlatformTransactionManager productTransactionManager(
        @Qualifier("productEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory){
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
}
