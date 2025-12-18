package com.CompanyName.ModelMapperPractice.NestedObjects.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Integer studentId;
    private String studentName;
    private String departmentName;
}
