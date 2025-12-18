package com.CompanyName.EmployeeDepartmentJsonNode.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @SequenceGenerator(
            name = "employeeSeq",
            sequenceName = "EMPLOYEE_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeSeq")
    private Integer employeeId;
    private String employeeName;

    @Column(unique = false)
    private Integer employeeSalary;

    @Column(unique = true, name = "unique_number_constraint")
    private long employeeNumber;

    @Column(unique = true, name = "unique_email_constraint")
    private String employeeEmail;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    @JsonBackReference
    private Department department;
}
