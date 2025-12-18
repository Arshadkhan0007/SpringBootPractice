package com.example.OrderMicroService.service;

import com.example.OrderMicroService.Integration.ProductFeign;
import com.example.OrderMicroService.dto.Product;
import com.example.OrderMicroService.dto.ProductRequestdto;
import com.example.OrderMicroService.dto.ProductResponseDto;
import com.example.OrderMicroService.exception.ProductNotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private final ProductFeign productFeign;
    private final RestTemplate restTemplate;
    private final RetryResponse response;

    public Product getProductById(int id) {
        return productFeign.getById(id);
    }

    public ProductResponseDto orderProduct(ProductRequestdto productRequestdto) {
        return productFeign.orderProduct(productRequestdto);
    }

    public Product getProduct(int id) {
      return response.getProductById(id);
    }

}
