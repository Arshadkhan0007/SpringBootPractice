package com.CompanyName.SimpleProjectEmployee2.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse <T>{
    private int status;
    private String message;
    private T data;
}
