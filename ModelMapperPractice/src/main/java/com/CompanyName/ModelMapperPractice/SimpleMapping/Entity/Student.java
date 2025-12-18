package com.CompanyName.ModelMapperPractice.SimpleMapping.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private Integer studentId;
    private String studentName;
    private String studentEmail;
    private double studentGrade;

}
