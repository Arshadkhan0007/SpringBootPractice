package com.CompanyName.ModelMapperPractice.SetPostConverter.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private Integer departmentId;
    private String departmentName;
    private int totalStudents;

}
