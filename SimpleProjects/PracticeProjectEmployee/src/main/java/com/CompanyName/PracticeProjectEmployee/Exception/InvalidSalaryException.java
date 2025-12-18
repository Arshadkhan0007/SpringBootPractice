package com.CompanyName.PracticeProjectEmployee.Exception;

public class InvalidSalaryException extends RuntimeException{
    public InvalidSalaryException(Integer num){
        super(num + " is invalid for Comparison, Should be at least above 1,00,000");
    }
}
