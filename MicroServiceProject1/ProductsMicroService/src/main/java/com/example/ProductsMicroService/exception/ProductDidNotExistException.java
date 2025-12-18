package com.example.ProductsMicroService.exception;

public class ProductDidNotExistException extends RuntimeException{

    public ProductDidNotExistException(String message) {
        super(message);
    }
}
