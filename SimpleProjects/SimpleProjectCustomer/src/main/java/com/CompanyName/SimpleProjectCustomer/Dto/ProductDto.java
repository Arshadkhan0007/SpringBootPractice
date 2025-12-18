package com.CompanyName.SimpleProjectCustomer.Dto;

import com.CompanyName.SimpleProjectCustomer.Model.Customer;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer prodId;

    @NotBlank(message = "Product name cannot be blank!")
    private String prodName;
    @NotNull(message = "Must provide product rating less than 5")
    @Max(value = 5, message = "Rating cannot be greater than 5")
    private Float prodRating;
    private String Description;

    private List<Integer> custIdList;
}
