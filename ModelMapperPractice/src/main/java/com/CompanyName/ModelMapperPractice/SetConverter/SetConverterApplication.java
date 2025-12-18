package com.CompanyName.ModelMapperPractice.SetConverter;

import com.CompanyName.ModelMapperPractice.SetConverter.Dto.DepartmentDto;
import com.CompanyName.ModelMapperPractice.SetConverter.Entity.Department;
import com.CompanyName.ModelMapperPractice.SetConverter.Entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class SetConverterApplication implements CommandLineRunner {

    private final ModelMapper mapper;

    public SetConverterApplication(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(SetConverterApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Department Entity -> Department DTO");
        List<Student> studentList = Arrays.asList(
                new Student(101, "Alex"),
                new Student(102, "Benson"),
                new Student(103, "Charlie")
        );
        Department department = new Department(5001, "CSE", studentList);
        System.out.println(department);
        DepartmentDto departmentDto = mapper.map(department, DepartmentDto.class);
        System.out.println(departmentDto);
    }
}
