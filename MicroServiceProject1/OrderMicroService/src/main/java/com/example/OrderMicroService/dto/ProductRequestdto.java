package com.example.OrderMicroService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductRequestdto {

    private String name;

    private int quantity;

}
