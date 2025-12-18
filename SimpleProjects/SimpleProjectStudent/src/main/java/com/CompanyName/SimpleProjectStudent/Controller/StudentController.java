package com.CompanyName.SimpleProjectStudent.Controller;

import com.CompanyName.SimpleProjectStudent.Dto.StudentDto;
import com.CompanyName.SimpleProjectStudent.Model.Student;
import com.CompanyName.SimpleProjectStudent.Response.ServiceResponse;
import com.CompanyName.SimpleProjectStudent.Service.StudentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class StudentController {
    StudentService service;

    //Constructor Dependency Injection
    public StudentController(StudentService service){
        this.service = service;
    }

    @GetMapping("/")
    public String homePage(){
        return "Hello and welcome!";
    }

    @GetMapping("/getStudById/{id}")
    public ResponseEntity<ServiceResponse<Student>> getStudById(@PathVariable Integer id){
        log.debug("Retrieving Student by id: {}", id);
        return service.getStudentById(id);
    }

    @GetMapping("/getStudByName/{name}")
    public ResponseEntity<ServiceResponse<List<Student>>> getStudByName(@PathVariable String name){
        log.debug("Retrieving Student by name: {}", name);
        return service.getStudentByName(name);
    }

    @PostMapping("/addStudent")
    public ResponseEntity<ServiceResponse<Student>> addStudent(@RequestBody @Valid StudentDto sDto){
        log.debug("Adding a student");
        return service.addStudent(sDto);
    }

    @PostMapping("/addStudents")
    public ResponseEntity<ServiceResponse<List<Student>>> addStudents(@RequestBody @Valid List<StudentDto> studentDtoList){
        log.debug("Adding {} students", studentDtoList.size());
        return service.addStudents(studentDtoList);
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<ServiceResponse<Student>> updateStudents(@PathVariable Integer id, @RequestBody @Valid StudentDto sDto){
        log.debug("updating a student");
        return service.updateStudent(id, sDto);
    }

    @DeleteMapping("/DeleteStudent/{id}")
    public ResponseEntity<ServiceResponse<Student>> deleteStudent(@PathVariable Integer id){
        log.debug("Deleting a student");
        return service.deleteStudById(id);
    }
}
