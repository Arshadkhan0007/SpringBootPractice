//package com.example.OrderMicroService.exception;
//
//import com.example.OrderMicroService.Integration.ProductFeign;
//import com.example.OrderMicroService.dto.Product;
//import com.example.OrderMicroService.dto.ProductRequestdto;
//import com.example.OrderMicroService.dto.ProductResponseDto;
//import org.springframework.cloud.openfeign.FallbackFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//public class FallbackHandling implements FallbackFactory<ProductFeign> {
//    @Override
//    public ProductFeign create(Throwable cause) {
//        return new ProductFeign() {
//            @Override
//            public ProductResponseDto orderProduct(ProductRequestdto productRequestdto) {
//                return new ProductResponseDto("Service is down","Fallback Product",0.00);
//            }
//
//            @Override
//            public Product getById(int id) {
//                return new Product(id,"Fallback Product",0.0);
//            }
//        };
//
//    }
//}
