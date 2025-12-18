package com.CompanyName.PracticeProjectEmployee.Controller;

import com.CompanyName.PracticeProjectEmployee.Exception.InvalidEmailException;
import com.CompanyName.PracticeProjectEmployee.Exception.MissingOrInvalidEmployeeDetailsException;
import com.CompanyName.PracticeProjectEmployee.Model.Employee;
import com.CompanyName.PracticeProjectEmployee.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @GetMapping("/")
    public String homePage(){
        return "Hello and Welcome!! \nThis is the Home Page";
    }

    EmployeeService service;

    public EmployeeController(EmployeeService service){
        this.service = service;
    }

    @PostMapping("/add_employee")
    public String addEmployee(@RequestBody Employee emp){
        service.addEmployee(emp);
        return "Employee ID : " + emp.getEmpId() +
                "\nEmployee Name : " + emp.getEmpName() +
                "\nHas been added!";
    }

    @PostMapping("/add_employees")
    public String addEmployees(@RequestBody List<Employee> empList){
        service.addEmployees(empList);
        return  empList.size() + " Employee(s) have been added!";
    }

    @GetMapping("/emp_id/{id}")
    public Employee findEmpByID(@PathVariable Integer id){
        return service.findEmpById(id);
    }

    @GetMapping("/emp_name/{name}")
    public List<Employee> findEmpByName(@PathVariable String name){
        return service.findEmpByName(name);
    }

    @GetMapping("/all_employees")
    public List<Employee> findAllEmp(){
        return service.findAllEmp();
    }

    @GetMapping("/dept_name/{deptName}")
    public List<Employee> findEmpByDeptName(@PathVariable String deptName){
        return service.findEmpByDept(deptName);
    }

    @GetMapping("/salary_greater/{num}")
    public List<Employee> salaryGreaterThan(@PathVariable Integer num){
        return service.salaryGreaterThan(num);
    }

    @GetMapping("/salary_lesser/{num}")
    public List<Employee> salaryLessThan(@PathVariable Integer num){
        return service.salaryLessThan(num);
    }

    @GetMapping("/emp_email/{keyword}")
    public List<Employee> findByEmpEmail(@PathVariable String keyword){
        return service.findByEmpEmail(keyword);
    }

    @PutMapping("/emp_update/{id}")
    public String updateEmp(@PathVariable Integer id, @RequestBody Employee emp){
        service.updateEmp(id, emp);
        return "Employee ID: " + id + " has been updated!" +
                "\n Employee Name: " + emp.getEmpName() +
                "\n Department Name: " + emp.getDeptName() +
                "\n Employee Salary: " + emp.getEmpSalary() +
                "\n Employee Email: " + emp.getEmpEmail();
    }

    @DeleteMapping("/delete_emp/{id}")
    public String deleteEmp(@PathVariable Integer id){
        service.deleteEmp(id);
        return "Employee ID: " + id + " Has been Deleted!";
    }
}
