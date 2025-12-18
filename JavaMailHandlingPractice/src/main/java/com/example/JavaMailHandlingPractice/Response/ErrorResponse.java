package com.example.JavaMailHandlingPractice.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse <T>{

    private LocalDateTime timestamp;
    private int status;
    private String errorType;
    private T message;

}
