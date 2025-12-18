package com.CompanyName.PracticeProjectEmployee.Exception;

public class InvaidDeptNameException extends RuntimeException{
    public InvaidDeptNameException(String deptName){
        super("Employee Department Name: " + deptName + " It should be one of these (Finance, Human Resource, IT, Operations, Research, Sales)");
    }
}
