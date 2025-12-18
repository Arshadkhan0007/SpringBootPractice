package com.CompanyName.SimplePracticeStuff;

import com.CompanyName.SimplePracticeStuff.Configuration.BankDetails;
import com.CompanyName.SimplePracticeStuff.Model.Person;
import com.CompanyName.SimplePracticeStuff.Repository.PersonRepo;
import jakarta.validation.Valid;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SimplePracticeStuffApplication implements CommandLineRunner {

    @Value("${spring.datasource.username}")
    private String username;

	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SimplePracticeStuffApplication.class, args);
        BankDetails details = context.getBean(BankDetails.class);
        System.out.println("Bank Name: " + details.getName());
        System.out.println("Bank Branch: " + details.getBranch());
	}
    @Autowired
    private PersonRepo repo;

    @Override
    public void run(String... args) throws Exception{
        Person p1 = new Person(null, "Alex Chomu", "chomu@gmail.com");
        repo.save(p1);
        System.out.println(p1);

        System.out.println(username);
    }

}
