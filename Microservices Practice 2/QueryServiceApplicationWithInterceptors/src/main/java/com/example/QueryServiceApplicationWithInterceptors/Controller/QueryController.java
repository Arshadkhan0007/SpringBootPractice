package com.example.QueryServiceApplicationWithInterceptors.Controller;

import com.example.QueryServiceApplicationWithInterceptors.Entity.Product;
import com.example.QueryServiceApplicationWithInterceptors.Service.QueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/products")
public class QueryController {

    private final QueryService service;

    public QueryController(QueryService service) {
        this.service = service;
    }

    @GetMapping("product/{prodId}")
    public ResponseEntity<Product> getProductById(@PathVariable int prodId) {
        return new ResponseEntity<>(service.getProductById(prodId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

}
