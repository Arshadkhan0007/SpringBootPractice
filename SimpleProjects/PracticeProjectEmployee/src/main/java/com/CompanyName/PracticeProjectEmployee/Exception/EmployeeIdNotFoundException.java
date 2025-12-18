package com.CompanyName.PracticeProjectEmployee.Exception;

public class EmployeeIdNotFoundException extends RuntimeException {
    public EmployeeIdNotFoundException(Integer id){
        super("Employee ID: " + id + " does not exist!");
    }
}
