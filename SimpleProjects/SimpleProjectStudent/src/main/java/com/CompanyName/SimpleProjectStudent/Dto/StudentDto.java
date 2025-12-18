package com.CompanyName.SimpleProjectStudent.Dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    @NotBlank(message = "Name cannot be blank!")
    private String studName;
    @NotBlank(message = "Department name cannot be blank!")
    private String studDept;
//    @Min(value = 999999999, message = "Number of digits less than 10")
    @NotNull(message = "Number cannot be null!")
    private Long studNum;
    @Email(message = "Invalid email format")
    private String studEmail;
}
