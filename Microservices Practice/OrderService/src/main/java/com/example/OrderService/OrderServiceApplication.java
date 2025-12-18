package com.example.OrderService;

import com.example.OrderService.Client.UserServiceClient;
import com.example.OrderService.Entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication implements CommandLineRunner{

//    private final RestTemplate restTemplate;
    private final UserServiceClient userServiceClient;

    public OrderServiceApplication(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
//        String url = "http://USER-SERVICE/users/user/101";
//        System.out.println(restTemplate.getForObject(url, User.class));

        System.out.println(userServiceClient.getUserById(101));
    }
}
