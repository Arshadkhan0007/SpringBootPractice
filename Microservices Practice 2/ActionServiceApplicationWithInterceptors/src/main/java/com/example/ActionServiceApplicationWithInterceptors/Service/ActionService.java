package com.example.ActionServiceApplicationWithInterceptors.Service;

import com.example.ActionServiceApplicationWithInterceptors.Entity.Product;
import com.example.ActionServiceApplicationWithInterceptors.Exception.ResourceNotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ActionService {

    private final RestTemplate restTemplate;

    public ActionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "QUERY-SERVICE-WITH-INTERCEPTOR", fallbackMethod = "getProductByIdFallback")
    @Retry(name = "QUERY-SERVICE-WITH-INTERCEPTOR")
    public Product getProductById(int prodId) {
        try {

            System.out.println("ACTION-SERVICE: Calling QUERY-SERVICE to get Product with Id: " + prodId);

            String url = "http://QUERY-SERVICE-WITH-INTERCEPTOR/query/products/product/{prodId}";
            return restTemplate.getForObject(url, Product.class, prodId);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }

    public Product getProductByIdFallback(int prodId, Throwable ex) {

        if(ex instanceof ResourceNotFoundException) {
            throw (ResourceNotFoundException) ex;
        }

        return new Product(0, "Fallback name", 0.0f);
    }
}
