package com.CompanyName.StudentDepartment.Exception;

import com.CompanyName.StudentDepartment.Response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<ErrorResponse<String>> handleResourseNotFound(ResourceNotFoundException ex,
                                                                        HttpServletRequest request){
        return new ResponseEntity<>(new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "NOT FOUND",
                request.getRequestURI(),
                ex.getMessage()
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DepartmentAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse<String>> handleDeptAlreadExists(DepartmentAlreadyExistsException ex,
                                                                        HttpServletRequest request){
        return new ResponseEntity<>(new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "BAD_REQUEST",
                request.getRequestURI(),
                ex.getMessage()
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidators(MethodArgumentNotValidException ex,
                                                          HttpServletRequest request){
        List<String> error = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + " : " + err.getDefaultMessage())
                .toList();

        return new ResponseEntity<>(new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "BAD REQUEST",
                request.getRequestURI(),
                error
        ), HttpStatus.BAD_REQUEST);
    }
}
