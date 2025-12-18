package com.CompanyName.StudentDepartment.MapperUtility;

import com.CompanyName.StudentDepartment.Dto.DepartmentRequestDto;
import com.CompanyName.StudentDepartment.Dto.DepartmentResponseDto;
import com.CompanyName.StudentDepartment.Dto.StudentResponseDto;
import com.CompanyName.StudentDepartment.Entity.Department;
import com.CompanyName.StudentDepartment.Entity.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentMapper {
    private final ObjectMapper mapper;
    private final StudentMapper studentMapper;

    public DepartmentMapper(ObjectMapper mapper, StudentMapper studentMapper) {
        this.mapper = mapper;
        this.studentMapper = studentMapper;
    }

    //RequestDto to Entity
    public Department toEntity(DepartmentRequestDto departmentRequestDto){
        return mapper.convertValue(departmentRequestDto, Department.class);
    }

    public List<Department> listToEntity(List<DepartmentRequestDto> departmentRequestDtoList){
        List<Department> departmentList = new ArrayList<>();
        for(DepartmentRequestDto departmentRequestDto : departmentRequestDtoList){
            Department department = mapper.convertValue(departmentRequestDto, Department.class);
            departmentList.add(department);
        }
        return departmentList;
    }

    //Entity to Dto
    public DepartmentResponseDto toDto(Department department){
        DepartmentResponseDto departmentResponseDto = mapper.convertValue(department, DepartmentResponseDto.class);

        if(department.getStudentList() != null) {
            departmentResponseDto.setTotalStudents(department.getStudentList().size());
        }
        if(department.getStudentList() != null) {
            List<StudentResponseDto> studentResponseDtoList = new ArrayList<>();
            for(Student student : department.getStudentList()){
                studentResponseDtoList.add(studentMapper.toDto(student));
            }
            departmentResponseDto.setStudentResponseDtoList(studentResponseDtoList);
        }

        return departmentResponseDto;
    }

    public List<DepartmentResponseDto> listToDto(List<Department> departmentList){
        List<DepartmentResponseDto> departmentResponseDtoList = new ArrayList<>();
        for(Department department : departmentList){
            DepartmentResponseDto departmentResponseDto = mapper.convertValue(department, DepartmentResponseDto.class);

            if(department.getStudentList() != null) {
                departmentResponseDto.setTotalStudents(department.getStudentList().size());
            }
            if(department.getStudentList() != null) {
                List<StudentResponseDto> studentResponseDtoList = new ArrayList<>();
                for(Student student : department.getStudentList()){
                    studentResponseDtoList.add(studentMapper.toDto(student));
                }
                departmentResponseDto.setStudentResponseDtoList(studentResponseDtoList);
            }

            departmentResponseDtoList.add(departmentResponseDto);
        }

        return departmentResponseDtoList;
    }
}
