package com.CompanyName.SimpleProjectStudent.Exceptions;

import com.CompanyName.SimpleProjectStudent.Response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex){
        log.error("Student id not found!");
        return new ResponseEntity<>(
                new ErrorResponse (LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        "NOT FOUND",
                        ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }
}
