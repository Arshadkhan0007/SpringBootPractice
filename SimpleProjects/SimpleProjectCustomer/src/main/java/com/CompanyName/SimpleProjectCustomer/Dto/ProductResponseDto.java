package com.CompanyName.SimpleProjectCustomer.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private String prodName;
    private Float prodRating;
    private String Description;

}
