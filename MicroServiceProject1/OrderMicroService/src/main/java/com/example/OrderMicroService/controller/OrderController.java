package com.example.OrderMicroService.controller;

import com.example.OrderMicroService.Integration.ProductFeign;
import com.example.OrderMicroService.dto.Product;
import com.example.OrderMicroService.dto.ProductRequestdto;
import com.example.OrderMicroService.dto.ProductResponseDto;
import com.example.OrderMicroService.exception.InvalidQuantityException;
import com.example.OrderMicroService.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        return new ResponseEntity<>(orderService.getProductById(id), HttpStatus.OK);

    }
    @PostMapping("/order")
    public ResponseEntity<ProductResponseDto> orderProduct(@RequestBody ProductRequestdto productRequestdto){
       if(productRequestdto.getQuantity()<1){
           throw new InvalidQuantityException("Please enter a valid quantity !!");
       }
       return new ResponseEntity<>(orderService.orderProduct(productRequestdto),HttpStatus.OK);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        return new ResponseEntity<>(orderService.getProduct(id),HttpStatus.OK);
    }

}
