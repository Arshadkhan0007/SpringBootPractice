package com.CompanyName.PracticeProjectEmployee.Exception;

public class EmployeeDeptNotFoundException extends RuntimeException{
    public EmployeeDeptNotFoundException(String deptName){
        super("Department Name: " + deptName + " not found");
    }
}
