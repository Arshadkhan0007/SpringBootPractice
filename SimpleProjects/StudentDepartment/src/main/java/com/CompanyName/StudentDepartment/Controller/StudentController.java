package com.CompanyName.StudentDepartment.Controller;

import com.CompanyName.StudentDepartment.Dto.StudentRequestDto;
import com.CompanyName.StudentDepartment.Dto.StudentResponseDto;
import com.CompanyName.StudentDepartment.Response.SuccessResponse;
import com.CompanyName.StudentDepartment.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String homePage(){
        return "Hello and Welcome!";
    }

    @GetMapping("/all_students")
    public ResponseEntity<SuccessResponse<List<StudentResponseDto>>> getAllStudents(){
        return service.getAllStudents();
    }
    @GetMapping("/student_id/{id}")
    public ResponseEntity<SuccessResponse<StudentResponseDto>> getStudentById(Integer id){
        return service.getStudentById(id);
    }

    @PostMapping("/add_student")
    public ResponseEntity<SuccessResponse<StudentResponseDto>> addStudent(@RequestBody @Valid StudentRequestDto studentRequestDto){
        return service.addStudent(studentRequestDto);
    }
    @PostMapping("add_all_student")
    public ResponseEntity<SuccessResponse<List<StudentResponseDto>>> addAllStudents(@RequestBody @Valid List<StudentRequestDto> studentRequestDtoList){
        return service.addAllStudents(studentRequestDtoList);
    }
}
