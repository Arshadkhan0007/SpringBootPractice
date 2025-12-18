package com.CompanyName.ModelMapperPractice.CustomMapping;

import com.CompanyName.ModelMapperPractice.CustomMapping.Dto.StudentDto;
import com.CompanyName.ModelMapperPractice.CustomMapping.Entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomMappingApplication implements CommandLineRunner {

    private final ModelMapper modelMapper;

    public CustomMappingApplication(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomMappingApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Student Entity -> Student DTO");
        Student s1 = new Student(101, "Alex", 8.76);
        System.out.println(s1);
        StudentDto studentDto = modelMapper.map(s1, StudentDto.class);
        System.out.println(studentDto);

        System.out.println("Student DTO -> Student Entity");
        StudentDto studentDto1 = new StudentDto(101, "Alex", 8.97);
        System.out.println(studentDto1);
        Student student = modelMapper.map(studentDto1, Student.class);
        System.out.println(student);
    }
}
