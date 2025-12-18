package com.example.ProductsMicroService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(ProductDidNotExistException.class)
    public ResponseEntity<Map<String,Object>> handleProductNotExist(ProductDidNotExistException ex){

        Map<String,Object>map=new HashMap<>();

        map.put("error","Product did not Exists");
        map.put("Message",ex.getMessage());
        map.put("Timestamp",System.currentTimeMillis());
        map.put("Status Code", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);



    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleProductNotFound(ProductNotFoundException ex){

        Map<String,Object>map=new HashMap<>();

        map.put("error","Product did not Found");
        map.put("Message",ex.getMessage());
        map.put("Timestamp",System.currentTimeMillis());
        map.put("Status Code", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);


    }

}
