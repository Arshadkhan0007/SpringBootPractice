package com.CompanyName.PracticeProjectEmployee.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Employee Name Not Found
    @ExceptionHandler(EmployeeNameNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEmpNameNotFound(EmployeeNameNotFoundException ex){
        Map<String, Object> map = new HashMap<>();
        map.put("Timestamp", LocalDateTime.now());
        map.put("Message", ex.getMessage());
        map.put("Status", HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    //Employee ID Not Found
    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleEmpIdNotFound(EmployeeIdNotFoundException ex){
        Map<String, Object> map = new HashMap<>();
        map.put("Timestamp", LocalDateTime.now());
        map.put("Message", ex.getMessage());
        map.put("Status", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    //Department name not found
    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleEmpDeptNotFound(EmployeeDeptNotFoundException ex){
        Map<String, Object> map = new HashMap<>();
        map.put("TimeStamp", LocalDateTime.now());
        map.put("Message", ex.getMessage());
        map.put("Status", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    //Salary too low for comparison
    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleSalaryTooLow(InvalidSalaryException ex){
        Map<String, Object> map = new HashMap<>();
        map.put("TimeStamp", LocalDateTime.now());
        map.put("Message", ex.getMessage());
        map.put("Status", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    //Employee Email does not exists
    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleEmailNotFound(EmployeeEmailNotFoundException ex){
        Map<String, Object> map = new HashMap<>();
        map.put("Timestamp", LocalDateTime.now());
        map.put("Message", ex.getMessage());
        map.put("Status", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    //Invalid email format
    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleInvalidEmail(InvalidEmailException ex){
        Map<String, Object> map = new HashMap<>();
        map.put("Timestamp", LocalDateTime.now());
        map.put("Message", ex.getMessage());
        map.put("Status", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    //Invalid Department name
    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleInvalidDeptName(InvaidDeptNameException ex){
        Map<String, Object> map = new HashMap<>();
        map.put("Timestamp", LocalDateTime.now());
        map.put("Message", ex.getMessage());
        map.put("Status", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    //Missing or invalid values
    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleMissingOrInvalid(MissingOrInvalidEmployeeDetailsException ex){
        Map<String, Object> map = new HashMap<>();
        map.put("Timestamp", LocalDateTime.now());
        map.put("Message", ex.getMessage());
        map.put("Status", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
