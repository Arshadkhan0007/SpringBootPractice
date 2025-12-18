package com.CompanyName.StudentDepartment.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequestDto {

    @NotBlank(message = "Department name cannot be blank!")
    private String departmentName;

}
