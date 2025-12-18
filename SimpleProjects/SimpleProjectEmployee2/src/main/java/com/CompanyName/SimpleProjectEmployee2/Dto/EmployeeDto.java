package com.CompanyName.SimpleProjectEmployee2.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Integer empId;
    @NotBlank(message = "Employee Name cannot be blank")
    @Column(length = 25)
    private String empName;
    @NotNull(message = "Employee Department cannot be Null")
    private Integer deptId;
    @NotNull(message = "Employee Phone Number cannot be null")
    @Min(value = 999999999, message = "Number of digits less than 10")
    @Column(unique = true)
    private Long empNum;
    @Email(message = "Invalid email, it should be in (example123@gmail.com) this format")
    @Column(unique = true)
    private String empEmail;
}
