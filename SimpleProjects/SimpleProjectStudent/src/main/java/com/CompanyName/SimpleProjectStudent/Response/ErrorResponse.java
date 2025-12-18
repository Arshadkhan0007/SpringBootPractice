package com.CompanyName.SimpleProjectStudent.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timeStamp;
    private int status;
    private String errorType;
    private String message;
}
