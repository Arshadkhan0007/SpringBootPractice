package com.CompanyName.SimpleProjectEmployee2.Controller;

import com.CompanyName.SimpleProjectEmployee2.Dto.DepartmentDto;
import com.CompanyName.SimpleProjectEmployee2.Model.Department;
import com.CompanyName.SimpleProjectEmployee2.Response.ServiceResponse;
import com.CompanyName.SimpleProjectEmployee2.Service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    private final DepartmentService service;

    //Construction Dependency Injection
    public DepartmentController(DepartmentService service){
        this.service = service;
    }

    @GetMapping("/all_depts")
    public ResponseEntity<ServiceResponse<List<DepartmentDto>>> getAllDepts(){
        return service.getALlDepts();
    }
    @GetMapping("/dept_id/{id}")
    public ResponseEntity<ServiceResponse<DepartmentDto>> getDeptById(@PathVariable Integer id){
        return service.getDeptById(id);
    }
    @GetMapping("/dept_name/{name}")
    public ResponseEntity<ServiceResponse<List<DepartmentDto>>> getDeptByName(@PathVariable String name){
        return service.getDeptByName(name);
    }

    @PostMapping("/add_department")
    public ResponseEntity<ServiceResponse<DepartmentDto>> addDepartment(@RequestBody @Valid DepartmentDto deptDto){
        return service.addDept(deptDto);
    }
    @PostMapping("/add_departments")
    public ResponseEntity<ServiceResponse<List<DepartmentDto>>> addDepartments(@RequestBody @Valid List<DepartmentDto> deptDtoList){
        return service.addDepts(deptDtoList);
    }

    @PutMapping("/update_dept/{id}")
    public ResponseEntity<ServiceResponse<DepartmentDto>> updateDepartment(@PathVariable Integer id, @RequestBody @Valid DepartmentDto deptDto){
        return service.updateDept(id, deptDto);
    }

    @DeleteMapping("/delete_dept/{id}")
    public ResponseEntity<ServiceResponse<DepartmentDto>> deleteDepartment(@PathVariable Integer id){
        return service.deleteDept(id);
    }
 }
