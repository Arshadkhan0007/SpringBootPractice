package com.CompanyName.PracticeProjectEmployee.Service;

import com.CompanyName.PracticeProjectEmployee.Exception.*;
import com.CompanyName.PracticeProjectEmployee.Model.Employee;
import com.CompanyName.PracticeProjectEmployee.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    EmployeeRepository repo;

    //Constructor DI
    public EmployeeService(EmployeeRepository repo){
        this.repo = repo;
    }

    //POST OPERATIONS
    //Adding an employee
    public void addEmployee(Employee emp){
        if(emp.getEmpName() == null || emp.getDeptName() == null || emp.getEmpSalary() == null || emp.getEmpEmail() == null){
            throw new MissingOrInvalidEmployeeDetailsException(emp.getEmpName(),
                    emp.getDeptName(),
                    emp.getEmpSalary(),
                    emp.getEmpEmail());
        }

        if(!emp.getEmpEmail().contains("@") || !emp.getEmpEmail().endsWith(".com")){
            throw new InvalidEmailException(emp.getEmpEmail());
        }

        if(!(emp.getDeptName().equals("IT") || emp.getDeptName().equals("Operations") || emp.getDeptName().equals("Marketing") || emp.getDeptName().equals("Human Resource") || emp.getDeptName().equals("Finance") || emp.getDeptName().equals("Research") || emp.getDeptName().equals("Sales"))){
            throw new InvaidDeptNameException(emp.getDeptName());
        }
        repo.save(emp);
    }

    //Adding multiple employees
    public void addEmployees(List<Employee> empList){
        for(Employee emp:empList){
            if(emp.getEmpName() == null || emp.getDeptName() == null || emp.getEmpSalary() == null || emp.getEmpEmail() == null){
                throw new MissingOrInvalidEmployeeDetailsException(emp.getEmpName(),
                        emp.getDeptName(),
                        emp.getEmpSalary(),
                        emp.getEmpEmail());
            }

            if(!emp.getEmpEmail().contains("@") || !emp.getEmpEmail().endsWith(".com")){
                throw new InvalidEmailException(emp.getEmpEmail());
            }

            if(!(emp.getDeptName().equals("IT") || emp.getDeptName().equals("Operations") || emp.getDeptName().equals("Marketing") || emp.getDeptName().equals("Human Resource") || emp.getDeptName().equals("Finance") || emp.getDeptName().equals("Research") || emp.getDeptName().equals("Sales"))){
                throw new InvaidDeptNameException(emp.getDeptName());
            }
        }
        repo.saveAll(empList);
    }

    // GET OPERATIONS
    //Retrieving an employee by empId
    public Employee findEmpById(Integer id){
        return repo.findById(id).orElseThrow(() -> new EmployeeIdNotFoundException(id));
    }

    //Retrieving an employee by name
    public List<Employee> findEmpByName(String name){
        if(repo.findByEmpName(name).isEmpty()){
            throw new EmployeeNameNotFoundException(name);
        }
        return repo.findByEmpName(name);
    }

    //Retrieving all employees
    public List<Employee> findAllEmp(){
        return repo.findAll();
    }

    //Retrieving employees as per deptName
    public List<Employee> findEmpByDept(String deptName){
        if(repo.findByDeptName(deptName).isEmpty()){
            throw new EmployeeDeptNotFoundException(deptName);
        }
        return repo.findByDeptName(deptName);
    }

    //Retrieving employees whose salary > num
    public List<Employee> salaryGreaterThan(Integer num){
        if(num < 100000){
            throw new InvalidSalaryException(num);
        }
        return repo.findByEmpSalaryGreaterThan(num);
    }

    //Retrieving employees whose salary < num
    public List<Employee> salaryLessThan(Integer num){
        if(num < 100000){
            throw new InvalidSalaryException(num);
        }
        return repo.findByEmpSalaryLessThan(num);
    }

    //Retrieving employees whose empEmail contains <keyword>
    public List<Employee> findByEmpEmail(String keyword){
        return repo.findByEmpEmailContaining(keyword);
    }

    // PUT OPERATIONS
    // Update employee
    public Employee updateEmp(Integer id, Employee updatedEmp){
        return repo.findById(id)
                .map(emp -> {
                    emp.setEmpName(updatedEmp.getEmpName());
                    emp.setDeptName(updatedEmp.getDeptName());
                    emp.setEmpSalary(updatedEmp.getEmpSalary());
                    emp.setEmpEmail(updatedEmp.getEmpEmail());
                    return repo.save(emp);
                }).orElseThrow(() -> new RuntimeException("Error!"));
    }

    //DELETE OPERATIONS
    //Deleting an employee by empId
    public void deleteEmp(Integer id){
        repo.deleteById(id);
    }
}
