package com.example.ProductsMicroService.controller;

import com.example.ProductsMicroService.dto.ProductRequestdto;
import com.example.ProductsMicroService.dto.ProductResponseDto;
import com.example.ProductsMicroService.entity.Product;
import com.example.ProductsMicroService.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@AllArgsConstructor

public class ProductController {

 private final ProductService productService;


 @PostMapping("/order")
 public ProductResponseDto orderProduct(@RequestBody ProductRequestdto productRequestdto){

   return  productService.createOrder(productRequestdto);

 }


 @GetMapping("/get/{id}")
    public Product getById(@PathVariable int id){


     return productService.getProductById(id);


 }




}
