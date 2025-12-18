package com.CompanyName.StudentDepartment.Service;

import com.CompanyName.StudentDepartment.Dto.StudentRequestDto;
import com.CompanyName.StudentDepartment.Dto.StudentResponseDto;
import com.CompanyName.StudentDepartment.Exception.ResourceNotFoundException;
import com.CompanyName.StudentDepartment.MapperUtility.StudentMapper;
import com.CompanyName.StudentDepartment.Repository.StudentRepository;
import com.CompanyName.StudentDepartment.Response.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repo;
    private final StudentMapper mapper;

    public StudentService(StudentRepository repo, StudentMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    //GET OPERATIONS
    //Retrieving all students
    public ResponseEntity<SuccessResponse<List<StudentResponseDto>>> getAllStudents(){
        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.OK.value(),
                "Retrieved all students successfully!!",
                mapper.listToDto(repo.findAll())
        ), HttpStatus.OK);
    }

    //Retrieving student by id
    public ResponseEntity<SuccessResponse<StudentResponseDto>> getStudentById(Integer id){
        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.OK.value(),
                "Successfully retrieved student with ID: " + id,
                mapper.toDto(repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Student ID: " + id + " does not exist!")))
        ), HttpStatus.OK);
    }

    //POST OPERATIONS
    //Adding a student
    public ResponseEntity<SuccessResponse<StudentResponseDto>> addStudent(StudentRequestDto studentRequestDto){
        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.CREATED.value(),
                "A student has be added successfully",
                mapper.toDto(repo.save(mapper.toEntity(studentRequestDto)))
        ), HttpStatus.CREATED);
    }

    //Add all students
    public ResponseEntity<SuccessResponse<List<StudentResponseDto>>> addAllStudents(List<StudentRequestDto> studentRequestDtoList){
        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.CREATED.value(),
                studentRequestDtoList.size() + " student(s) have been added successfully",
                mapper.listToDto(repo.saveAll(mapper.listToEntity(studentRequestDtoList)))
        ), HttpStatus.CREATED);
    }
}
