package com.CompanyName.FileHandlingPractice.Exception;

import com.CompanyName.FileHandlingPractice.Response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(EmptyFileException.class)
    public ResponseEntity<ErrorResponse<String>> handleEmptyFile(EmptyFileException ex, HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "BAD REQUEST",
                request.getRequestURI(),
                ex.getMessage()
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNonExistentException.class)
    public ResponseEntity<ErrorResponse<String>> handleFileNonExistent(FileNonExistentException ex, HttpServletRequest request){
        return new ResponseEntity<>(new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "BAD REQUEST",
                request.getRequestURI(),
                ex.getMessage()
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFileNameException.class)
    public ResponseEntity<ErrorResponse<String>> handleInvalidFilePath(InvalidFileNameException ex, HttpServletRequest request){
        return new ResponseEntity<>( new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "BAD REQUEST",
                request.getRequestURI(),
                ex.getMessage()
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnableToCreateRootDirectoryException.class)
    public ResponseEntity<ErrorResponse<String>> handleUnableToCreateRootDict(UnableToCreateRootDirectoryException ex,
                                                                              HttpServletRequest request){
        return new ResponseEntity<>(new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL SERVER ERROR",
                request.getRequestURI(),
                ex.getMessage()
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnableToCreateStreamsException.class)
    public ResponseEntity<ErrorResponse<String>> handleUnableToCreateStreams(UnableToCreateStreamsException ex, HttpServletRequest request){
        return new ResponseEntity<>( new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL SERVE ERROR",
                request.getRequestURI(),
                ex.getMessage()
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnableToUploadFileException.class)
    public ResponseEntity<ErrorResponse<String>> handleUnableToUploadFile(UnableToUploadFileException ex, HttpServletRequest request){
        return new ResponseEntity<>(new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL SERVER ERROR",
                request.getRequestURI(),
                ex.getMessage()
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
