package com.CompanyName.PracticeProjectEmployee.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
    @SequenceGenerator(name = "emp_seq", sequenceName = "EMP_SEQ", allocationSize = 1)
    private Integer empId;
    private String empName;
    private String deptName;
    private Integer empSalary;
    private String empEmail;
}
