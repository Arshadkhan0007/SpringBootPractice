package com.example.QueryServiceApplicationWithInterceptors.Service;

import com.example.QueryServiceApplicationWithInterceptors.Entity.Product;
import com.example.QueryServiceApplicationWithInterceptors.Exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService {

    private final List<Product> productList = List.of(
            new Product(101, "Product_A", 5.0f),
            new Product(102, "Product_B", 4.7f),
            new Product(103, "Product_C", 3.9f),
            new Product(104, "Product_D", 4.5f),
            new Product(105, "Product_E", 3.9f)
            );

    public Product getProductById(int id) {
        return productList.stream()
                .filter(product -> product.getProductId() == id)
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Product with Id: " + id + " does not exist"));
    }

    public List<Product> getAllProducts() {
        return productList;
    }

}
