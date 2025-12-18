package com.CompanyName.BasicTreeNodePractice.Service;

import com.CompanyName.BasicTreeNodePractice.Dto.StudentRequestDto;
import com.CompanyName.BasicTreeNodePractice.Dto.StudentResponseDto;
import com.CompanyName.BasicTreeNodePractice.Mapper.StudentMapper;
import com.CompanyName.BasicTreeNodePractice.Repository.StudentRepository;
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
    //Retrieve all students
    public List<StudentResponseDto> getAllStudents(){
        return mapper.listToDto(repo.findAll());
    }

    //POST OPERATIONS
    //Adding a student
    public StudentResponseDto addStudent(StudentRequestDto studentRequestDto){
        return mapper.toDto(repo.save(mapper.toEntity(studentRequestDto)));
    }
}
