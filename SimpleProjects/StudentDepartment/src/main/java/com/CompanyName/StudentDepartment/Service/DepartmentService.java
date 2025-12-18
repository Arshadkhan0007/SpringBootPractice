package com.CompanyName.StudentDepartment.Service;

import com.CompanyName.StudentDepartment.Dto.DepartmentRequestDto;
import com.CompanyName.StudentDepartment.Dto.DepartmentResponseDto;
import com.CompanyName.StudentDepartment.Entity.Department;
import com.CompanyName.StudentDepartment.Exception.DepartmentAlreadyExistsException;
import com.CompanyName.StudentDepartment.Exception.ResourceNotFoundException;
import com.CompanyName.StudentDepartment.MapperUtility.DepartmentMapper;
import com.CompanyName.StudentDepartment.Repository.DepartmentRepository;
import com.CompanyName.StudentDepartment.Response.SuccessResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository repo;
    private final DepartmentMapper mapper;

    public DepartmentService(DepartmentRepository repo, DepartmentMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    //GET OPERATIONS
    //Retrieving all Departments
    public ResponseEntity<SuccessResponse<List<DepartmentResponseDto>>> getAllDepartments(){
        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.OK.value(),
                "Successfully retrieved all departments",
                mapper.listToDto(repo.findAll())
        ), HttpStatus.OK);
    }

    //Retrieving department by id
    public ResponseEntity<SuccessResponse<DepartmentResponseDto>> getDepartmentById(Integer id){
        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.OK.value(),
                "Successfully retrieved department with ID: " + id,
                mapper.toDto(repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Department ID: " + id + " does not exist!")))
        ), HttpStatus.OK);
    }

    //POST OPERATIONS
    public ResponseEntity<SuccessResponse<DepartmentResponseDto>> addDepartment(DepartmentRequestDto departmentRequestDto){
        try {
            Department department = repo.save(mapper.toEntity(departmentRequestDto));
            DepartmentResponseDto departmentResponseDto = mapper.toDto(department);

            return new ResponseEntity<>(new SuccessResponse<>(
                    HttpStatus.OK.value(),
                    "Successfully added a department",
                    departmentResponseDto
            ), HttpStatus.OK);

        } catch (DataIntegrityViolationException ex){
            throw new DepartmentAlreadyExistsException("Department " +  departmentRequestDto.getDepartmentName() + " already exists");
        }
    }

    public ResponseEntity<SuccessResponse<List<DepartmentResponseDto>>> addAllDepartment(List<DepartmentRequestDto> departmentRequestDtoList){
        List<DepartmentResponseDto> departmentResponseDtoList = new ArrayList<>();
        for(DepartmentRequestDto departmentRequestDto : departmentRequestDtoList){
            DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
            try{
                departmentResponseDto = mapper.toDto(repo.save(mapper.toEntity(departmentRequestDto)));
            } catch (DataIntegrityViolationException ex){
                throw new DepartmentAlreadyExistsException("Department" + departmentRequestDto.getDepartmentName() + " already exists!");
            }
            departmentResponseDtoList.add(departmentResponseDto);
        }
        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.CREATED.value(),
                departmentResponseDtoList.size() + " department(s) added successfully",
                departmentResponseDtoList
        ), HttpStatus.CREATED);
    }
}
