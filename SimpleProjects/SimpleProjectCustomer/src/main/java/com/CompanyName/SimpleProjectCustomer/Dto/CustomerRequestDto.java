package com.CompanyName.SimpleProjectCustomer.Dto;

import com.CompanyName.SimpleProjectCustomer.Model.Product;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {

    @NotBlank(message = "Customer name cannot be null")
    @Size(min = 3, max = 30, message = "Customer name should have at least 3 characters and at most 30 characters")
    private String custName;

    private Boolean primeMember;

    @Min(value = 999999999, message = "Customer number must have 10 digits")
    private Long custNum;

    @Email(message = "Invalid email format, it should be in (Example123@gmail.com) this format")
    private String custEmail;

    private Map<Integer, String> prodIdMap;
}
