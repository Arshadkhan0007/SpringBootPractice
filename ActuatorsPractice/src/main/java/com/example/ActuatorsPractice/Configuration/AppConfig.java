package com.example.ActuatorsPractice.Configuration;

import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public HttpExchangeRepository httpExchangeRepository() {
        // Keeps a small history of recent HTTP requests and responses in memory
        return new InMemoryHttpExchangeRepository();
    }

}
