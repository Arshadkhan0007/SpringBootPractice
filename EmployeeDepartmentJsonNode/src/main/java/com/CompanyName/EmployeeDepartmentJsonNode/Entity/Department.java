package com.CompanyName.EmployeeDepartmentJsonNode.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @SequenceGenerator(
            name = "departmentSeq",
            sequenceName = "DEPARTMENT_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departmentSeq")
    private Integer departmentId;
    @Column(unique = true)
    private String departmentName;

    @OneToMany(mappedBy = "department")
    @JsonManagedReference
    private List<Employee> employeeList;
}
