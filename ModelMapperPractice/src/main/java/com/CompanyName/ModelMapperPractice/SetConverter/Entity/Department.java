package com.CompanyName.ModelMapperPractice.SetConverter.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    private Integer departmentId;
    private String departmentName;
    private List<Student> studentList;

}
