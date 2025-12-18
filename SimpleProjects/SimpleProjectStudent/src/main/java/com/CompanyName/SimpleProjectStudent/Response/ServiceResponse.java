package com.CompanyName.SimpleProjectStudent.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceResponse <T>{
    private int status;
    private String message;
    private T data;
}
