package com.CompanyName.SimpleProjectCustomer.Dto;

import com.CompanyName.SimpleProjectCustomer.Model.Customer;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    @NotBlank(message = "Product name cannot be blank")
    private String prodName;

    @Max(value = 5, message = "Product ratings cannot be greater than 5")
    private Float prodRating;

    @NotBlank(message = "Product Description cannot be null")
    private String Description;

    private List<Integer> custIdList;
}
