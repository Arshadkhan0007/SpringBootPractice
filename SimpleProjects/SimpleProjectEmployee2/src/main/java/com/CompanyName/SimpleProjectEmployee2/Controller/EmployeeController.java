package com.CompanyName.SimpleProjectEmployee2.Controller;

import com.CompanyName.SimpleProjectEmployee2.Dto.EmployeeDto;
import com.CompanyName.SimpleProjectEmployee2.Response.ServiceResponse;
import com.CompanyName.SimpleProjectEmployee2.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService service;

    //Constructor Dependency injection
    public EmployeeController(EmployeeService service){
        this.service = service;
    }

    @GetMapping("/")
    public String homePage(){
        return "Hello and Welcome to this shit!!";
    }

    @GetMapping("/all_emp")
    public ResponseEntity<ServiceResponse<List<EmployeeDto>>> getAllEmp(){
        return service.getAllEmp();
    }
    @GetMapping("/emp_id/{id}")
    public ResponseEntity<ServiceResponse<EmployeeDto>> getEmpById(@PathVariable Integer id){
        return service.getEmpById(id);
    }
    @GetMapping("/emp_name/{name}")
    public ResponseEntity<ServiceResponse<List<EmployeeDto>>> getEmpByName(@PathVariable String name){
        return service.getEmpByName(name);
    }

    @PostMapping("/add_employee")
    public ResponseEntity<ServiceResponse<EmployeeDto>> addEmployee(@RequestBody @Valid EmployeeDto empDto){
        return service.addEmployee(empDto);
    }
    @PostMapping("/add_employees")
    public ResponseEntity<ServiceResponse<List<EmployeeDto>>> addEmployees(@RequestBody @Valid List<EmployeeDto> empDtoList){
        return service.addEmployees(empDtoList);
    }

    @PutMapping("/update_emp/{id}")
    public ResponseEntity<ServiceResponse<EmployeeDto>> updateEmployee(@PathVariable Integer id, @RequestBody @Valid EmployeeDto empDto){
        return service.updateEmp(id, empDto);
    }

    @DeleteMapping("/delete_emp/{id}")
    public ResponseEntity<ServiceResponse<EmployeeDto>> deleteEmployee(@PathVariable Integer id){
        return service.deleteEmp(id);
    }
}
