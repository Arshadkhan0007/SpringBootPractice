package com.example.ActionServiceWithRestTemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ActionServiceWithRestTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActionServiceWithRestTemplateApplication.class, args);
	}

}
