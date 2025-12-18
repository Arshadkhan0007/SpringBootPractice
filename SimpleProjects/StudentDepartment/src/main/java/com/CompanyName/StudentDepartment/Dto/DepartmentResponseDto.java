package com.CompanyName.StudentDepartment.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponseDto {

    private Integer departmentId;
    private String departmentName;
    private int totalStudents;
    private List<StudentResponseDto> studentResponseDtoList;

}
