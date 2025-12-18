package com.CompanyName.BasicTreeNodePractice.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LaptopAlreadyExistsException.class)
    public String handleLaptopAlreadyExists(LaptopAlreadyExistsException ex){
        return ex.getMessage();
    }
}
