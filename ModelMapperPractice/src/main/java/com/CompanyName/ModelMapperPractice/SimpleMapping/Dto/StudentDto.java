package com.CompanyName.ModelMapperPractice.SimpleMapping.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Integer studentId;
    private String studentName;
    private int studentGrade; //ModelMapper can convert primitive types automatically

}
