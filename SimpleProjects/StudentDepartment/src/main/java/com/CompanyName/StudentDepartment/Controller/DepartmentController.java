package com.CompanyName.StudentDepartment.Controller;

import com.CompanyName.StudentDepartment.Dto.DepartmentRequestDto;
import com.CompanyName.StudentDepartment.Dto.DepartmentResponseDto;
import com.CompanyName.StudentDepartment.Response.SuccessResponse;
import com.CompanyName.StudentDepartment.Service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/all_departments")
    public ResponseEntity<SuccessResponse<List<DepartmentResponseDto>>> getAllSepartments(){
        return service.getAllDepartments();
    }
    @GetMapping("/department_id/{id}")
    public ResponseEntity<SuccessResponse<DepartmentResponseDto>> getDepartmentById(Integer id){
        return service.getDepartmentById(id);
    }

    @PostMapping("/add_department")
    public ResponseEntity<SuccessResponse<DepartmentResponseDto>> addDepartment(@RequestBody @Valid DepartmentRequestDto departmentRequestDto){
        return service.addDepartment(departmentRequestDto);
    }
    @PostMapping("/add_all_departments")
    public ResponseEntity<SuccessResponse<List<DepartmentResponseDto>>> addAllDepartment(@RequestBody @Valid List<DepartmentRequestDto> departmentRequestDtoList){
        return service.addAllDepartment(departmentRequestDtoList);
    }
}
