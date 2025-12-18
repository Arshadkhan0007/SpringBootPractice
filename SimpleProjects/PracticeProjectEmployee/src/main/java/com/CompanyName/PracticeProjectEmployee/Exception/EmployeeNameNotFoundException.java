package com.CompanyName.PracticeProjectEmployee.Exception;

public class EmployeeNameNotFoundException extends RuntimeException{
    public EmployeeNameNotFoundException(String name){
        super("Employee Name: " + name + " does not exist!");
    }
}
