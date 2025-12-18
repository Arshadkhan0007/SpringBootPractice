package com.CompanyName.EmployeeDepartmentJsonNode.Controller;

import com.CompanyName.EmployeeDepartmentJsonNode.Service.DepartmentService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/all_departments")
    public ResponseEntity<ObjectNode> getAllDepartments(){
        return service.getAllDepartments();
    }
    @GetMapping("/department/{id}")
    public ResponseEntity<ObjectNode> getDepartmentById(@PathVariable Integer id){
        return service.getDepartmentById(id);
    }

    @PostMapping("/add_department")
    public ResponseEntity<ObjectNode> addDepartment(@RequestBody JsonNode departmentNode){
        return service.addDepartment(departmentNode);
    }

    @PutMapping("/update_department/{id}")
    public ResponseEntity<ObjectNode> updateDepartment(@PathVariable Integer id, @RequestBody JsonNode departmentNode){
        return service.updateDepartment(id, departmentNode);
    }

    @PatchMapping("/patch_employee/{id}")
    public ResponseEntity<ObjectNode> patchDepartment(@PathVariable Integer id, @RequestBody JsonNode departmentNode){
        return service.patchDepartment(id, departmentNode);
    }

    @DeleteMapping("/delete_department/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Integer id){
        return new ResponseEntity<>(service.deleteDepartment(id), HttpStatus.OK);
    }
}
