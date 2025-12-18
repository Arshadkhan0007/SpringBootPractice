package com.example.ActuatorsPractice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActuatorsPracticeApplication implements CommandLineRunner {

    @Value("${info.app.description}")
    public String appName;

	public static void main(String[] args) {
		SpringApplication.run(ActuatorsPracticeApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println(appName);
    }
}
