package com.CompanyName.EmployeeDepartmentJsonNode.Service;

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
public class EmployeeService {
    private final EmployeeRepository repo;
    private final DepartmentRepository deptRepo;
    private final ObjectMapper mapper;

    public EmployeeService(EmployeeRepository repo, DepartmentRepository deptRepo, ObjectMapper mapper) {
        this.repo = repo;
        this.deptRepo = deptRepo;
        this.mapper = mapper;
    }

    //GET OPERATIONS
    public ResponseEntity<ObjectNode> getEmployeeById(Integer id){
        Employee employee = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee ID: " + id + " does not exist!"));
        ObjectNode employeeNode = mapper.createObjectNode();
        employeeNode.put("employee_name", employee.getEmployeeName());
        employeeNode.put("employee_email", employee.getEmployeeEmail());
        employeeNode.put("employee_department", employee.getDepartment().getDepartmentName());
        employeeNode.put("employee_salary", employee.getEmployeeSalary());

        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.set("employee", employeeNode);
        responseNode.put("status", HttpStatus.OK.value());
        responseNode.put("message", "Successfully retrieved employee with ID: " + id);

        return new ResponseEntity<>(responseNode, HttpStatus.OK);
    }

    public ResponseEntity<ObjectNode> getAllEmployees(){
        List<Employee> employeeList = repo.findAll();
        ArrayNode employeeArrayNode = mapper.createArrayNode();
        for(Employee employee : employeeList){
            ObjectNode employeeNode = mapper.createObjectNode();
            employeeNode.put("employee_name", employee.getEmployeeName());
            employeeNode.put("employee_email", employee.getEmployeeEmail());
            if(employee.getDepartment() != null) {
                employeeNode.put("employee_department", employee.getDepartment().getDepartmentName());
            }
            employeeArrayNode.add(employeeNode);
        }
        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put("employees", employeeArrayNode);
        responseNode.put("status", HttpStatus.OK.value());
        responseNode.put("message", "Successfully retrieved all employees");

        return new ResponseEntity<>(responseNode, HttpStatus.OK);
    }

    //POST OPERATIONS
    public ResponseEntity<ObjectNode> addEmployee(JsonNode employeeNode){
        Employee employee = new Employee();
        employee.setEmployeeName(employeeNode.get("employee_name").asText());
        employee.setEmployeeEmail(employeeNode.get("employee_salary").asText());
        employee.setEmployeeNumber(employeeNode.get("employee_number").asInt());
        employee.setEmployeeSalary(employeeNode.get("employee_salary").asInt());
        employee.setDepartment(deptRepo.findById(employeeNode.get("department_id").asInt())
                .orElseThrow(() -> new ResourceNotFoundException("Department ID: " + employeeNode.get("department_id").asInt() + " does not exist!")));
        repo.save(employee);

        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put("employee", mapper.valueToTree(employee));
        responseNode.put("status", HttpStatus.OK.value());
        responseNode.put("message", "Successfully added 1 employee");

        return new ResponseEntity<>(responseNode, HttpStatus.OK);
    }

    public ResponseEntity<ObjectNode> addAllEmployees(ArrayNode employeeArrayNode){
        Iterator<JsonNode> iterator = employeeArrayNode.elements();
        List<Employee> employeeList = new ArrayList<>();
        while(iterator.hasNext()){
            JsonNode employeeNode = iterator.next();
            Employee employee = new Employee();
            employee.setEmployeeName(employeeNode.get("employee_name").asText());
            employee.setEmployeeEmail(employeeNode.get("employee_salary").asText());
            employee.setEmployeeNumber(employeeNode.get("employee_number").asInt());
            employee.setEmployeeSalary(employeeNode.get("employee_salary").asInt());
            employee.setDepartment(deptRepo.findById(employeeNode.get("department_id").asInt())
                    .orElseThrow(() -> new ResourceNotFoundException("Department ID: " + employeeNode.get("department_id").asInt() + " does not exist!")));
            employeeList.add(employee);
        }
        repo.saveAll(employeeList);

        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.set("employees", mapper.valueToTree(employeeList));
        responseNode.put("status", HttpStatus.OK.value());
        responseNode.put("message", "Successfully added " + employeeList.size() + " employees");

        return new ResponseEntity<>(responseNode, HttpStatus.OK);
    }

    //PATCH OPERATION
    public ResponseEntity<ObjectNode> patchEmployee(Integer id, JsonNode patch){
        Employee employee = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee ID: " + id + " does not exist!"));
        if(patch.has("employee_name")){
            employee.setEmployeeName(patch.get("employee_name").asText());
        }
        if(patch.has("employee_email")){
            employee.setEmployeeEmail(patch.get("employee_email").asText());
        }
        if(patch.has("employee_number")){
            employee.setEmployeeNumber(patch.get("employee_number").asInt());
        }
        if(patch.has("employee_salary")){
            employee.setEmployeeSalary(patch.get("employee_salary").asInt());
        }
        if(patch.has("department_id")){
            employee.setDepartment(deptRepo.findById(patch.get("department_id").asInt())
                    .orElseThrow(() -> new ResourceNotFoundException("Department ID: " + patch.get("department_id").asInt() + " does not exist!")));
        }
        repo.save(employee);

        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put("employee", mapper.valueToTree(employee));
        responseNode.put("status", HttpStatus.OK.value());
        responseNode.put("message", "Successfully patched an employee");
        return new ResponseEntity<>(responseNode, HttpStatus.OK);
    }

    //POST OPERATION
    public ResponseEntity<ObjectNode> updateEmployee(Integer id, JsonNode employeeNode) {
        Employee employee = repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Employee ID: " + id + " does not exist"));
        employee.setEmployeeName(employeeNode.get("employee_name").asText(null));
        employee.setEmployeeEmail(employeeNode.get("employee_email").asText(null));
        employee.setEmployeeNumber(employeeNode.get("employee_number").asInt(0));
        employee.setEmployeeSalary(employeeNode.get("employee_salary").asInt(0));

        if (employeeNode.get("department_id") != null) {
            employee.setDepartment(deptRepo.findById(employeeNode.get("department_id").asInt())
                    .orElseThrow(() -> new ResourceNotFoundException("Department ID: " + employeeNode.get("department_id").asInt() + " does not exist!")));
        } else {
            employee.setDepartment(null);
        }
        repo.save(employee);

        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.set("employee", mapper.valueToTree(employee));
        responseNode.put("status", HttpStatus.OK.value());
        responseNode.put("message", "Employee with ID: " + id + " has be updated successfully");

        return new ResponseEntity<>(responseNode, HttpStatus.OK);
    }

    //DELETE OPERATION
    public String deleteEmployee(Integer id){
        repo.deleteById(id);
        return "Employee has been deleted successfully";
    }

}
