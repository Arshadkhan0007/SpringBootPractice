package com.CompanyName.ModelMapperPractice.NestedObjects;

import com.CompanyName.ModelMapperPractice.NestedObjects.Dto.StudentDto;
import com.CompanyName.ModelMapperPractice.NestedObjects.Entity.Department;
import com.CompanyName.ModelMapperPractice.NestedObjects.Entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NestedObjectsApplication implements CommandLineRunner {

    private final ModelMapper mapper;

    public NestedObjectsApplication(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(NestedObjectsApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Student Entity -> Student DTO");
        Department department1 = new Department(5001, "CSE");
        Student s1 = new Student(101, "Alex", department1);
        System.out.println(s1);
        StudentDto studentDto = mapper.map(s1, StudentDto.class);
        System.out.println(studentDto);

        System.out.println("Student DTO -> Student Entity");
        StudentDto studentDto1 = new StudentDto(101, "Alex", "CSE");
        System.out.println(studentDto1);
        Student student = mapper.map(studentDto1, Student.class);
        System.out.println(student);
    }
}
