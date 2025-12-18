package com.example.OrderMicroService.Integration;

import com.example.OrderMicroService.dto.Product;
import com.example.OrderMicroService.dto.ProductRequestdto;
import com.example.OrderMicroService.dto.ProductResponseDto;
//import com.example.OrderMicroService.exception.FallbackHandling;
import com.example.OrderMicroService.exception.FallbackHandling1;
import com.example.OrderMicroService.exception.GlobalExceptionHandling;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ProductsMicroService",configuration = GlobalExceptionHandling.class, fallback = FallbackHandling1.class)
public interface ProductFeign  {

    @PostMapping("product/order")
    public ProductResponseDto orderProduct(@RequestBody ProductRequestdto productRequestdto);


    @GetMapping("product/get/{id}")
    public Product getById(@PathVariable int id);



}
