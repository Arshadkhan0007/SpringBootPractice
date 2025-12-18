package com.example.OrderMicroService.service;


import com.example.OrderMicroService.dto.Product;
import com.example.OrderMicroService.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@Slf4j
public class RetryResponse {

    private final RestTemplate restTemplate;

    public RetryResponse(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Retryable(
            value = {HttpServerErrorException.class},
            maxAttempts = 3,
            backoff = @Backoff(2000)
    )
    public Product getProductById(int id) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://PRODUCTSMICROSERVICE/product/get/{id}")
                .buildAndExpand(id)
                .toUri();

        return restTemplate
                .exchange(uri, HttpMethod.GET, HttpEntity.EMPTY, Product.class)
                .getBody();
    }

    @Recover
    public Product recover(HttpClientErrorException.NotFound e, int id) {
        log.info("Recover called for id {}", id);
        return new Product();  // fallback response
    }
}
