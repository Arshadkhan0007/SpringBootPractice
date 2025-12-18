package com.CompanyName.StudentDepartment.Exception;

public class DepartmentAlreadyExistsException extends RuntimeException{
    public DepartmentAlreadyExistsException(String message) {
        super(message);
    }
}
