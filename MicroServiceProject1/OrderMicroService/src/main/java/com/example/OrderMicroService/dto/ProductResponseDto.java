package com.example.OrderMicroService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductResponseDto {

    private String Status;

    private String name;

    private double TotalPrice;

}
