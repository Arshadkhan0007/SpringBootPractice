package com.example.ActionServiceApplication.Exception;

public class CustomServiceUnavailableException extends RuntimeException{
    public CustomServiceUnavailableException(String message) {
        super(message);
    }
}
