package com.example.ActionServiceApplicationWIthRetryLogic.Exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
