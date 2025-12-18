package com.CompanyName.StudentDepartment.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDto {
    private Integer studentId;
    private String studentName;
    private String departmentName;
}
