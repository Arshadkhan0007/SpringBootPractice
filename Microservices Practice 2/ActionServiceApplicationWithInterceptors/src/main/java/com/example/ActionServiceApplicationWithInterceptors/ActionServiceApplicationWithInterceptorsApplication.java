package com.example.ActionServiceApplicationWithInterceptors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ActionServiceApplicationWithInterceptorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActionServiceApplicationWithInterceptorsApplication.class, args);
	}

}
