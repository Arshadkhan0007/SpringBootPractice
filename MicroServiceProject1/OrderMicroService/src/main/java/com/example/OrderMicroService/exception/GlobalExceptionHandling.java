package com.example.OrderMicroService.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<Map<String,Object>> handleProductNotFoundException(FeignException.NotFound ex){

        Map<String,Object>map=new HashMap<>();

        map.put("error","Product did not Found");
        map.put("Message",ex.getMessage());
        map.put("Timestamp",System.currentTimeMillis());
        map.put("Status Code", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);


    }
    @ExceptionHandler(FeignException.InternalServerError.class)
    public ResponseEntity<Map<String,Object>> handleInternalServerException(FeignException.InternalServerError ex){

        Map<String,Object>map=new HashMap<>();

        map.put("error","Product did not Found");
        map.put("Message",ex.getMessage());
        map.put("Timestamp",System.currentTimeMillis());
        map.put("Status Code", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);


    }

    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<Map<String,Object>> handleQuantityInvalidException(InvalidQuantityException ex){

        Map<String,Object>map=new HashMap<>();

        map.put("error","Invalid Quantity");
        map.put("Message",ex.getMessage());
        map.put("Timestamp",System.currentTimeMillis());
        map.put("Status Code", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);


    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String,Object>> productNotFound(ProductNotFoundException ex){

        Map<String,Object>map=new HashMap<>();

        map.put("error","product not found");
        map.put("Message",ex.getMessage());
        map.put("Timestamp",System.currentTimeMillis());
        map.put("Status Code", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);


    }

}
