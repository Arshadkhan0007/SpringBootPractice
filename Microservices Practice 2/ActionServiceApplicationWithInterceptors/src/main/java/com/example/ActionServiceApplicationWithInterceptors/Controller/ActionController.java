package com.example.ActionServiceApplicationWithInterceptors.Controller;

import com.example.ActionServiceApplicationWithInterceptors.Entity.Product;
import com.example.ActionServiceApplicationWithInterceptors.Service.ActionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/action/interceptors")
public class ActionController {

    private final ActionService service;

    public ActionController(ActionService service) {
        this.service = service;
    }

    @GetMapping("/product/{prodId}")
    public ResponseEntity<Product> getProductById(@PathVariable int prodId) {
        return new ResponseEntity<>(service.getProductById(prodId), HttpStatus.OK);
    }
}
