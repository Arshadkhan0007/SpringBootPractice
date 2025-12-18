package com.CompanyName.EmployeeDepartmentJsonNode.Service;

import com.CompanyName.EmployeeDepartmentJsonNode.Entity.Department;
import com.CompanyName.EmployeeDepartmentJsonNode.Entity.Employee;
import com.CompanyName.EmployeeDepartmentJsonNode.Exception.ResourceNotFoundException;
import com.CompanyName.EmployeeDepartmentJsonNode.Repository.DepartmentRepository;
import com.CompanyName.EmployeeDepartmentJsonNode.Repository.EmployeeRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository repo;
    private final ObjectMapper mapper;
    private final EmployeeRepository empRepo;

    public DepartmentService(DepartmentRepository repo, ObjectMapper mapper, EmployeeRepository empRepo) {
        this.repo = repo;
        this.mapper = mapper;
        this.empRepo = empRepo;
    }

    //GET OPERATIONS
    public ResponseEntity<ObjectNode> getAllDepartments(){
        List<Department> departmentList = repo.findAll();

        ArrayNode departmentArrayNode = mapper.createArrayNode();

        for(Department department : departmentList){
            ObjectNode departmentNode = mapper.createObjectNode();
            departmentNode.put("department_name", department.getDepartmentName());

            if(department.getEmployeeList() != null){
                departmentNode.put("total_employees", department.getEmployeeList().size());

                ArrayNode employeeArrayNode = mapper.createArrayNode();
                for(Employee employee : department.getEmployeeList()){
                    ObjectNode employeeNode = mapper.createObjectNode();
                    employeeNode.put("employee_name", employee.getEmployeeName());
                    employeeNode.put("employee_email", employee.getEmployeeEmail());
                    employeeArrayNode.add(employeeNode);
                }
                departmentNode.set("employees", employeeArrayNode);
            }
            departmentArrayNode.add(departmentNode);
        }

        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.set("departments", departmentArrayNode);
        responseNode.put("status", HttpStatus.OK.value());
        responseNode.put("message", "Successfully retrieved all employees");

        return new ResponseEntity<>(responseNode, HttpStatus.OK);
    }

    public ResponseEntity<ObjectNode> getDepartmentById(Integer id){
        Department department = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department ID: " + id + " does not exist!"));

        ObjectNode DepartmentNode = mapper.createObjectNode();

        DepartmentNode.put("department_name", department.getDepartmentName());

        if(department.getEmployeeList() != null) {
            DepartmentNode.put("total_employees", department.getEmployeeList().size());
            ArrayNode employeeArrayNode = mapper.createArrayNode();
            for (Employee employee : department.getEmployeeList()) {
                ObjectNode employeeNode = mapper.createObjectNode();
                employeeNode.put("employee_name", employee.getEmployeeName());
                employeeNode.put("employee_salary", employee.getEmployeeSalary());
                employeeArrayNode.add(employeeNode);
            }
            DepartmentNode.set("employees", employeeArrayNode);
        }

        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.set("department", DepartmentNode);
        responseNode.put("status", HttpStatus.OK.value());
        responseNode.put("message", "Successfully retrieved department with ID: " + id);

        return new ResponseEntity<>(responseNode, HttpStatus.OK);
    }

    //POST OPERATIONS
    public ResponseEntity<ObjectNode> addDepartment(JsonNode departmentNode){
        Department department = new Department();
        department.setDepartmentName(departmentNode.get("department_name").asText());

        if(departmentNode.get("employees") != null) {
            Iterator<JsonNode> iterator = departmentNode.get("employees").elements();
            List<Employee> employeeList = new ArrayList<>();
            while (iterator.hasNext()) {
                Integer id = iterator.next().asInt();
                employeeList.add(empRepo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Employee ID: " + id + " does not exist!")));
            }
            department.setEmployeeList(employeeList);
        }

        department = repo.save(department);

        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.set("department", mapper.valueToTree(department));
        responseNode.put("status", HttpStatus.CREATED.value());
        responseNode.put("message", "1 Department has be added successfully");

        return new ResponseEntity<>(responseNode, HttpStatus.CREATED);
    }

    //PATCH OPERATIONS
    public ResponseEntity<ObjectNode> patchDepartment(Integer id, JsonNode patch){
        Department department = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department ID: " + id + " does not exist!"));
        if(patch.has("department_name")){
            department.setDepartmentName(patch.get("department_name").asText());
        }
        if(patch.has("employees")){
            List<Employee> employeeList = new ArrayList<>();
            Iterator<JsonNode> iterator = patch.get("employees").elements();
            while(iterator.hasNext()){
                Integer employeeId = iterator.next().asInt();
                employeeList.add(empRepo.findById(employeeId)
                        .orElseThrow(() -> new ResourceNotFoundException("Employee ID: " + employeeId + " does not exist!")));
            }
            department.setEmployeeList(employeeList);
        }
        repo.save(department);

        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.set("department", mapper.valueToTree(department));
        responseNode.put("status", HttpStatus.OK.value());
        responseNode.put("message", "Successfully patched department with ID: " + id);

        return new ResponseEntity<>(responseNode, HttpStatus.OK);
    }

    //POST METHOD
    public ResponseEntity<ObjectNode> updateDepartment(Integer id, JsonNode departmentNode){
        Department department = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department ID: " + id + " does not exist!"));

        if(departmentNode.has("department_name")){
            department.setDepartmentName(departmentNode.get("department_name").asText());
        } else{
            department.setDepartmentName(null);
        }

        if(departmentNode.has("employees")){
            List<Employee> employeeList = new ArrayList<>();
            Iterator<JsonNode> iterator = departmentNode.get("employees").elements();
            while(iterator.hasNext()){
                Integer employeeId = iterator.next().asInt();
                employeeList.add(empRepo.findById(employeeId)
                        .orElseThrow(() -> new ResourceNotFoundException("Employee ID: " + employeeId + " does not exist")));
            }
            department.setEmployeeList(employeeList);
        } else {
            department.setEmployeeList(null);
        }

        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.set("department", mapper.valueToTree(department));
        responseNode.put("status", HttpStatus.OK.value());
        responseNode.put("message", "Department with ID: " + id + " has been updated successfully");

        return new ResponseEntity<>(responseNode, HttpStatus.OK);
    }

    //DELETE OPERATION
    public String deleteDepartment(Integer id){
        repo.deleteById(id);
        return "Department deleted successfully";
    }
}
