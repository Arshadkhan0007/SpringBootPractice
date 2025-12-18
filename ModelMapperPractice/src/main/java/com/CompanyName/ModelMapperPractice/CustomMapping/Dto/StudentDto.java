package com.CompanyName.ModelMapperPractice.CustomMapping.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Integer studId;
    private String studName;
    private double grade;

}
