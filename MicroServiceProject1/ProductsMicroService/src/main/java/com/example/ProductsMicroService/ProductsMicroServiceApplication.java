package com.example.ProductsMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductsMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsMicroServiceApplication.class, args);
	}

}
