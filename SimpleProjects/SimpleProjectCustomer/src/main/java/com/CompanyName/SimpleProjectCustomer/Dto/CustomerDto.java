package com.CompanyName.SimpleProjectCustomer.Dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Integer custId;

    @NotBlank(message = "Customer Name cannot be blank!")
    @Size(min = 3, max = 25, message = "Name should be more than 3 characters and less than 25 characters")
    private String custName;

    @NotNull(message = "Must mention prime membership!")
    private Boolean primeMember;

    @NotNull(message = "Customer number cannot be null")
    @Min(value = 999999999, message = "Number of digits in phone number must be 10")
    private Long custNum;

    @Email(message = "Invalid Email, it should be in (Example123@gmail.com) this format!")
    @NotNull(message = "Email cannot be null")
    private String custEmail;

    private Map<Integer, String> prodIdMap;
}
