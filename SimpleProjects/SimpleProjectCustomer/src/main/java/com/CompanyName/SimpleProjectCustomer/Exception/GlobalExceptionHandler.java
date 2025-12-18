package com.CompanyName.SimpleProjectCustomer.Exception;

import com.CompanyName.SimpleProjectCustomer.Response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex){
        return new ResponseEntity<>(new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "NOT FOUND",
                ex.getMessage()
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<List<String>>> handleMethodArgsNotValid(MethodArgumentNotValidException ex){
        List<String> error = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + " : " + err.getDefaultMessage())
                .toList();
        return new ResponseEntity<>(new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "BAD REQUEST",
                error
        ), HttpStatus.BAD_REQUEST);
    }
}
