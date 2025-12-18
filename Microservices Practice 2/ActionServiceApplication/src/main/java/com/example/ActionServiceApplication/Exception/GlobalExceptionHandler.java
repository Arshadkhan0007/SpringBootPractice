package com.example.ActionServiceApplication.Exception;

import com.example.ActionServiceApplication.Response.ErrorResponse;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    // Either FeignExceptions can be handled here like so
    // or let the ErrorDecoder handle them
    // keep in mind that globalExceptionHandler will override ErrorDecoder
//    @ExceptionHandler(FeignException.NotFound.class)
//    public ResponseEntity<ErrorResponse> handleNotFound(FeignException.NotFound ex) {
//
//        System.out.println("Message: " + ex.getMessage());
//
//        return new ResponseEntity<>(new ErrorResponse(
//                HttpStatus.NOT_FOUND.value(),
//                "NOT_FOUND",
//                ex.getMessage()
//        ), HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "NOT FOUND",
                ex.getMessage()
        ), HttpStatus.NOT_FOUND);
    }

}
