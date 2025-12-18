package com.CompanyName.PracticeProjectEmployee.Exception;

public class MissingOrInvalidEmployeeDetailsException extends RuntimeException{
    public MissingOrInvalidEmployeeDetailsException(String empName, String deptName, Integer empSalary, String empEmail){
        super("Provided details are missing or invalid" + " (Employee Name: " + empName
        + " | Department Name: " + deptName
        + " | Salary: " + empSalary
        + " | Email: " + empEmail + ")");
    }
}
