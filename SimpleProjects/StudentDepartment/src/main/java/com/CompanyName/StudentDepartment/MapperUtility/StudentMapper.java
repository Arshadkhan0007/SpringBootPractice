package com.CompanyName.StudentDepartment.MapperUtility;

import com.CompanyName.StudentDepartment.Dto.DepartmentRequestDto;
import com.CompanyName.StudentDepartment.Dto.StudentRequestDto;
import com.CompanyName.StudentDepartment.Dto.StudentResponseDto;
import com.CompanyName.StudentDepartment.Entity.Department;
import com.CompanyName.StudentDepartment.Entity.Student;
import com.CompanyName.StudentDepartment.Repository.DepartmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StudentMapper {
    private final ObjectMapper mapper;
    private final DepartmentRepository deptRepo;

    public StudentMapper(ObjectMapper mapper, DepartmentRepository deptRepo) {
        this.mapper = mapper;
        this.deptRepo = deptRepo;
    }

    //RequestDto to Entity
    public Student toEntity(StudentRequestDto studentRequestDto){
        Student student = mapper.convertValue(studentRequestDto, Student.class);

        DepartmentRequestDto departmentRequestDto = studentRequestDto.getDepartmentRequest();
        if(departmentRequestDto != null){
            Department department = deptRepo.findByDepartmentName(departmentRequestDto.getDepartmentName());
            if(department != null){
                student.setDepartment(department);
            } else {
                department = new Department();
                department.setDepartmentName(departmentRequestDto.getDepartmentName());
                student.setDepartment(department);
            }
        }

        return student;
    }

    public List<Student> listToEntity(List<StudentRequestDto> studentRequestDtoList){
        List<Student> studentList = new ArrayList<>();
        for(StudentRequestDto studentRequestDto : studentRequestDtoList){
            Student student = mapper.convertValue(studentRequestDto, Student.class);

            DepartmentRequestDto departmentRequestDto = studentRequestDto.getDepartmentRequest();
            if(departmentRequestDto != null){
                Department department = deptRepo.findByDepartmentName(departmentRequestDto.getDepartmentName());
                if(department != null){
                    student.setDepartment(department);
                } else {
                    department = new Department();
                    department.setDepartmentName(departmentRequestDto.getDepartmentName());
                    student.setDepartment(department);
                }
            }
            studentList.add(student);
        }
        return studentList;
    }

    //Entity to ResponseDto
    public StudentResponseDto toDto(Student student){
        StudentResponseDto studentResponseDto = mapper.convertValue(student, StudentResponseDto.class);
        if(student.getDepartment() != null) {
            studentResponseDto.setDepartmentName(student.getDepartment().getDepartmentName());
        }
        return studentResponseDto;
    }

    public List<StudentResponseDto> listToDto(List<Student> studentList){
        List<StudentResponseDto> studentResponseDtoList = new ArrayList<>();
        for(Student student : studentList){
            StudentResponseDto studentResponseDto = mapper.convertValue(student, StudentResponseDto.class);
            studentResponseDto.setDepartmentName(student.getDepartment().getDepartmentName());
            studentResponseDtoList.add(studentResponseDto);
        }
        return studentResponseDtoList;
    }
}
