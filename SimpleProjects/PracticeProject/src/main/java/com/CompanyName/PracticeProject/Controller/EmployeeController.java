package com.CompanyName.PracticeProject.Controller;

import com.CompanyName.PracticeProject.Model.Employee;
import com.CompanyName.PracticeProject.Repository.EmployeeRepository;
import com.CompanyName.PracticeProject.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @GetMapping("/")
    public String homePage(){
        return "Hello and Welcome!! \n This here is the Home Page";
    }

    EmployeeService service;

    public EmployeeController(EmployeeService service){
        this.service = service;
    }

    @PostMapping("/add_employee")
    public String addEmployee(@RequestBody Employee emp){
        service.addEmployee(emp);
        return "Employee Name - " + emp.getEmpName() + "\nEmployee Id - " + emp.getEmpId() + "" +
                "\nhas been added";
    }

    @PostMapping("/add_employees")
    @ResponseBody
    public String addEmployees(@RequestBody List<Employee> emp_list){
        service.addEmployees(emp_list);
        return emp_list.size() +  " Employees Added";
    }

    @GetMapping("/{id}")
    public Employee findEmployeeById(@PathVariable Integer id){
        return service.findEmployeeById(id);
    }

    @GetMapping("/all_employees")
    public List<Employee> findAllEmployees(){
        return service.findAllEmployees();
    }

    @GetMapping("/employee/{emp_name}")
    public List<Employee> findEmpByName(@PathVariable String emp_name){
        return service.findEmployeeByName(emp_name);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public String updateEmployee(@PathVariable Integer id, @RequestBody Employee emp){
        service.updateEmployee(id, emp);
        return "Employee Updated\n" +
                "Employee_id = " + id +
                "\nEmployee_name = " + emp.getEmpName() +
                "\nEmployee_deptName = " + emp.getDeptName() +
                "\nEmployee_salary = " + emp.getSalary() +
                "\nEmployee_email = " + emp.getEmpEmail();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteEmployee(@PathVariable Integer id){
        service.deleteEmployee(id);
        return "Employee - " + id + ", Has been deleted";
    }
}
