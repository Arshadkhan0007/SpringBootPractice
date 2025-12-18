package com.CompanyName.PracticeProjectEmployee.Exception;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(String empEmail){
        super("Employee Email: " + empEmail + " is invalid, It should be in this format (Example123@gmail.com)");
    }
}
