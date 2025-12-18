package com.CompanyName.PracticeProjectCustomer.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {
    private Integer customerId;

    @NotBlank(message = "Customer name cannot be blank")
    private String customerName;

    private List<ProductDto> productDtoList;
}
