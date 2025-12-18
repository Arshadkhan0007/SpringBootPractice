package com.CompanyName.EmployeeDepartmentJsonNode.Exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final ObjectMapper mapper;

    public GlobalExceptionHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ObjectNode handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request){
        ObjectNode errorResponse = mapper.createObjectNode();
        errorResponse.put("timestamp", String.valueOf(LocalDateTime.now()));
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("error_type", "NOT FOUND");
        errorResponse.put("url", request.getRequestURI());
        errorResponse.put("message", ex.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ObjectNode handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request) {
        ObjectNode errorResponse = mapper.createObjectNode();
        errorResponse.put("timestamp", String.valueOf(LocalDateTime.now()));
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error_type", "BAD REQUEST");
        errorResponse.put("url", request.getRequestURI());
        errorResponse.put("message", ex.getMessage());
        return errorResponse;
    }
}
