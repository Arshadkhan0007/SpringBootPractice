package com.CompanyName.SimpleProjectStudent.Service;

import com.CompanyName.SimpleProjectStudent.Dto.StudentDto;
import com.CompanyName.SimpleProjectStudent.Exceptions.ResourceNotFoundException;
import com.CompanyName.SimpleProjectStudent.Model.Student;
import com.CompanyName.SimpleProjectStudent.Repository.StudentRepository;
import com.CompanyName.SimpleProjectStudent.Response.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StudentService {
    StudentRepository repo;

    // Constructor Dependency Injection
    public StudentService(StudentRepository repo){
        this.repo = repo;
    }

    //POST METHODS
    //Adding a student
    public ResponseEntity<ServiceResponse<Student>> addStudent(StudentDto sDto){
        log.debug("Inside addStudent(), to insert a student");

        Student s = new Student(null, sDto.getStudName(), sDto.getStudDept(), sDto.getStudNum(), sDto.getStudEmail());
        repo.save(s);
        log.debug("Student id: {} inserted Successfully",s.getStudId());

        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                "Inserted Successfully!",
                s), HttpStatus.OK);
    }

    //Adding multiple users
    public ResponseEntity<ServiceResponse<List<Student>>> addStudents(List<StudentDto> studDtoList){
        log.debug("Inside addStudents(), to insert {} students", studDtoList.size());

        List<Student> studList = new ArrayList<>();
        for(StudentDto sDto : studDtoList){
            Student s = new Student();
            s.setStudName(sDto.getStudName());
            s.setStudDept(sDto.getStudDept());
            s.setStudNum(sDto.getStudNum());
            s.setStudEmail(sDto.getStudEmail());
            studList.add(s);
        }
        log.debug("{} students inserted successfully", studList.size());

        return new ResponseEntity<>( new ServiceResponse<>(
                HttpStatus.OK.value(),
                studList.size() + " Students inserted Successfully!",
                studList), HttpStatus.OK);
    }

    //GET OPERATIONS
    //Retrieving student by Id
    public ResponseEntity<ServiceResponse<Student>> getStudentById(Integer id){
        log.debug("Inside getStudentById(), to retrieve Student Id: {}", id);
        Student s = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student Id: " + id + " does not exist!"));

        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                "Retrieved Successfully!",
                s
        ), HttpStatus.OK);
    }

    //Retrieving student by name
    public ResponseEntity<ServiceResponse<List<Student>>> getStudentByName(String name){
        log.debug("Inside getStudentByName(), to retrieve Student Name: {}", name);
        Optional<List<Student>> sList = repo.findByStudName(name);

        if(sList.isPresent()){
            return new ResponseEntity<>(new ServiceResponse<>(
                    HttpStatus.OK.value(),
                    "Success!",
                    sList.get()
            ), HttpStatus.OK);
        }
        throw new ResourceNotFoundException("");
    }

    //PUT OPERATIONS
    //Updating employee with employee Id
    public ResponseEntity<ServiceResponse<Student>> updateStudent(Integer id, StudentDto updatedStudDto){
        Student presentStud = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student Id: " + id + " does not exist!"));

        presentStud.setStudName(updatedStudDto.getStudName());
        presentStud.setStudDept(updatedStudDto.getStudDept());
        presentStud.setStudNum(updatedStudDto.getStudNum());
        presentStud.setStudEmail(updatedStudDto.getStudEmail());

        Student updatedStudent = repo.save(presentStud);

        log.debug("Student Id: {} has been updated", id);

        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                "Modification Successful!",
                updatedStudent
        ), HttpStatus.OK);
    }

    //DELETE OPERATIONS
    //Deleting a Student with Id
    public ResponseEntity<ServiceResponse<Student>> deleteStudById(Integer id){
        log.debug("Inside deleteStudById(), to delete student with Id: {}", id);
        Student s = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student Id: " + id + " does not exist!"));

        repo.deleteById(id);
        log.debug("Student with id: {} has been deleted!", id);

        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                "Deletion Successful!",
                s
        ), HttpStatus.OK);
    }

}
