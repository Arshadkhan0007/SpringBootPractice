package com.example.ActionServiceApplicationWIthRetryLogic.Exception;

import com.example.ActionServiceApplicationWIthRetryLogic.Response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        System.out.println("ACTION-SERVICE: Handling ResourceNotFoundException, reason: " + ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "NOT FOUND",
                ex.getMessage()
        ), HttpStatus.NOT_FOUND);
    }

}
