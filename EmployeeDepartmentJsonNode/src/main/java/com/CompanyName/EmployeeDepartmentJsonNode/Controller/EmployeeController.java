package com.CompanyName.EmployeeDepartmentJsonNode.Controller;

import com.CompanyName.EmployeeDepartmentJsonNode.Service.EmployeeService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String homePage(){
        return "Hello and Welcome!";
    }

    @GetMapping("/all_employees")
    public ResponseEntity<ObjectNode> getAllEmployees(){
        return service.getAllEmployees();
    }
    @GetMapping("/employee/{id}")
    public ResponseEntity<ObjectNode> getEmployee(@PathVariable Integer id){
        return service.getEmployeeById(id);
    }

    @PostMapping("/add_employee")
    public ResponseEntity<ObjectNode> addEmployee(@RequestBody JsonNode employeeNode){
        return service.addEmployee(employeeNode);
    }
    @PostMapping("/add_all_employees")
    public ResponseEntity<ObjectNode> addAllEmployees(@RequestBody ArrayNode employeeArrayNode){
        return service.addAllEmployees(employeeArrayNode);
    }

    @PutMapping("/update_employee/{id}")
    public ResponseEntity<ObjectNode> updateEmployee(@PathVariable Integer id, @RequestBody JsonNode employeeNode){
        return service.updateEmployee(id, employeeNode);
    }

    @PatchMapping("/patch_employee/{id}")
    public ResponseEntity<ObjectNode> patchEmployee(@PathVariable Integer id, @RequestBody JsonNode employeeNode){
        return service.patchEmployee(id, employeeNode);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id){
        return new ResponseEntity<>(service.deleteEmployee(id), HttpStatus.OK);
    }
}
