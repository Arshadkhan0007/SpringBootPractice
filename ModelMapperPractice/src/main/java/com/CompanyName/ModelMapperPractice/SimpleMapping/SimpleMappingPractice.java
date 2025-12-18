package com.CompanyName.ModelMapperPractice.SimpleMapping;

import com.CompanyName.ModelMapperPractice.SimpleMapping.Dto.StudentDto;
import com.CompanyName.ModelMapperPractice.SimpleMapping.Entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleMappingPractice implements CommandLineRunner {

    private final ModelMapper mapper;

    public SimpleMappingPractice(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleMappingPractice.class);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Student Entity -> Student DTO");
        Student s1 = new Student(101, "Alex", "Alex@gmail.com", 7.94);
        System.out.println(s1);
        StudentDto studentDto = mapper.map(s1, StudentDto.class);
        System.out.println(studentDto);

        System.out.println("Student DTO -> Student Entity");
        StudentDto studentDto1 = new StudentDto(101, "Alex", 7);
        System.out.println(studentDto1);
        Student s2 = mapper.map(studentDto1, Student.class);
        System.out.println(s2);
    }
}
