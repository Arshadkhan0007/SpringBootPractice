package com.CompanyName.PracticeProject.Service;

import com.CompanyName.PracticeProject.Model.Employee;
import com.CompanyName.PracticeProject.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repo;

    // Constructor DI
    public EmployeeService(EmployeeRepository repo){
        this.repo = repo;
    }

    //Adding an employee
    public Employee addEmployee(Employee emp){
        return repo.save(emp);
    }

    //Adding multiple employees
    public void addEmployees(List<Employee> emp_list){
        repo.saveAll(emp_list);
    }

    //Find employee by ID
    public Employee findEmployeeById(Integer id){
        return repo.findById(id).orElse(null);
    }

    //Find employee by name
    public List<Employee> findEmployeeByName(String empName){
        return repo.findByEmpName(empName);
    }

    //Return all employees
    public List<Employee> findAllEmployees(){
        return repo.findAll();
    }

    //Update an employee
    public Employee updateEmployee(Integer id, Employee updated_emp){
        return repo.findById(id)
                .map(emp -> {
                    emp.setEmpName(updated_emp.getEmpName());
                    emp.setDeptName(updated_emp.getDeptName());
                    emp.setSalary(updated_emp.getSalary());
                    emp.setEmpEmail(updated_emp.getEmpEmail());
                    return addEmployee(emp);
                })
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));
    }

    //Delete an employee
    public void deleteEmployee(Integer id){
        repo.deleteById(id);
    }
}
