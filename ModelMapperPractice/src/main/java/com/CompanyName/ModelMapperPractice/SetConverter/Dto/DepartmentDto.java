package com.CompanyName.ModelMapperPractice.SetConverter.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private Integer Id;
    private String Name;
    private int totalStudents;

}
