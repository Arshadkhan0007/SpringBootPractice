package com.CompanyName.PracticeProjectEmployee.Exception;

public class EmployeeEmailNotFoundException extends RuntimeException{
    public EmployeeEmailNotFoundException(String empEmail){
        super(empEmail + " does not exist!");
    }
}
