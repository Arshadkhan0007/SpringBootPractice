package com.example.OrderMicroService.exception;

import com.example.OrderMicroService.Integration.ProductFeign;
import com.example.OrderMicroService.dto.Product;
import com.example.OrderMicroService.dto.ProductRequestdto;
import com.example.OrderMicroService.dto.ProductResponseDto;

public class FallbackHandling1 implements ProductFeign {

    @Override
    public ProductResponseDto orderProduct(ProductRequestdto productRequestdto) {
        return new ProductResponseDto("Service down","Fallback",0.00);
    }

    @Override
    public Product getById(int id) {
        return new Product(0,"NUll",0.00);
    }
}
