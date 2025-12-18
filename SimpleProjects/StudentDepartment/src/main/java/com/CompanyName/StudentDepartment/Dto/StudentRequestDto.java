package com.CompanyName.StudentDepartment.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDto {

    @NotBlank(message = "Student name cannot be blank!")
    private String studentName;

    @Email(message = "Invalid email format!, this is how it should be (Example123@gmail.com)")
    @NotBlank(message = "Student Email cannot be blank!")
    private String studentEmail;

    @Min(value = 999999999, message = "Number must contain 10 digits!")
    private Long studentNumber;

    private DepartmentRequestDto departmentRequest;
}
