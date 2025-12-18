package com.example.SpringSecurityPractice1.Exception;

import com.example.SpringSecurityPractice1.Response.ErrorResponse;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse<String>> handleBadCredentialsException(BadCredentialsException ex, HttpServletRequest request){

        log.info("BadCredentialsException");

        return new ResponseEntity<>(new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "BAD_REQUEST",
                request.getRequestURI(),
                ex.getMessage()
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResponse<String>> handleSignatureException(SignatureException ex, HttpServletRequest request){

        log.info("SignatureException");

        return new ResponseEntity<>(new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                "UNAUTHORIZED",
                request.getRequestURI(),
                ex.getMessage()
        ), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorResponse<String>> handleMalformedJwtException(MalformedJwtException ex, HttpServletRequest request){

        log.info("MalformedJwtException");

        return new ResponseEntity<>(new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                "UNAUTHORIZED",
                request.getRequestURI(),
                ex.getMessage()
        ), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<ErrorResponse<String>> handleUnsupportedJwtException(UnsupportedJwtException ex, HttpServletRequest request){

        log.info("UnsupportedJwtException");

        return new ResponseEntity<>(new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                "UNAUTHORIZED",
                request.getRequestURI(),
                ex.getMessage()
        ), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request){

        log.info("IllegalArgumentException");

        return new ResponseEntity<>(new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "BAD_REQUEST",
                request.getRequestURI(),
                ex.getMessage()
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse<String>> handleJwtException(JwtException ex, HttpServletRequest request){

        log.info("JwtException");

        return new ResponseEntity<>(new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR",
                request.getRequestURI(),
                ex.getMessage()
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
