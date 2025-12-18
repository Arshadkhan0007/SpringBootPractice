package com.CompanyName.PracticeProjectCustomer.Dto;

import com.CompanyName.PracticeProjectCustomer.Model.Product;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {

    @NotBlank(message = "Customer name cannot be blank!")
    private String customerName;

    private List<Integer> productIdList;

    @Email(message = "Invalid email format, it should be something like this (Example123@gmail.com)")
    private String customerEmail;

    @NotBlank(message = "Customer username cannot be blank!")
    private String customerUsername;

    @NotBlank(message = "Customer password cannot be blank!")
    private String customerPassword;
}
