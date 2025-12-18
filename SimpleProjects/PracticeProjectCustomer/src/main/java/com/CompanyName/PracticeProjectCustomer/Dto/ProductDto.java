package com.CompanyName.PracticeProjectCustomer.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Integer ProductId;

    @NotBlank(message = "Product name cannot be blank!")
    private String productName;

    @Min(value = 1, message = "Product price cannot be less than 1")
    private float productPrice;
}
