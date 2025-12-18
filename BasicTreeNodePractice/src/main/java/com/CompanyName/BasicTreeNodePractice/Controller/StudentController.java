package com.CompanyName.BasicTreeNodePractice.Controller;

import com.CompanyName.BasicTreeNodePractice.Dto.StudentRequestDto;
import com.CompanyName.BasicTreeNodePractice.Dto.StudentResponseDto;
import com.CompanyName.BasicTreeNodePractice.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String homePage(){
        return "Hello and welcome!!";
    }
    @GetMapping("/all_students")
    public List<StudentResponseDto> getAllStudents(){
        return service.getAllStudents();
    }

    @PostMapping("/add_student")
    public StudentResponseDto addStudent(@RequestBody @Valid StudentRequestDto studentRequestDto){
        return service.addStudent(studentRequestDto);
    }
}
