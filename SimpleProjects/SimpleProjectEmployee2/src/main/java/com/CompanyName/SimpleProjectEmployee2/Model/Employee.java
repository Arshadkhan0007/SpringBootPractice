package com.CompanyName.SimpleProjectEmployee2.Model;

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
            name="emp_seq1",
            sequenceName = "EMP_SEQ1",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="emp_seq1")
    private Integer empId;
    private String empName;

    // Many Employees -> One Department
    @ManyToOne
    @JoinColumn(name = "dept_id") // This basically name of the join column
    private Department empDept;

    private Long empNum;
    private String empEmail;
}
