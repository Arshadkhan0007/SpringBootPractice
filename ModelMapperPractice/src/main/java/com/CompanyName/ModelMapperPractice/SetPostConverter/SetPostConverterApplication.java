package com.CompanyName.ModelMapperPractice.SetPostConverter;

import com.CompanyName.ModelMapperPractice.SetPostConverter.Dto.DepartmentDto;
import com.CompanyName.ModelMapperPractice.SetPostConverter.Entity.Department;
import com.CompanyName.ModelMapperPractice.SetPostConverter.Entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SetPostConverterApplication implements CommandLineRunner {

    private final ModelMapper mapper;

    public SetPostConverterApplication(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(SetPostConverterApplication.class);
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
