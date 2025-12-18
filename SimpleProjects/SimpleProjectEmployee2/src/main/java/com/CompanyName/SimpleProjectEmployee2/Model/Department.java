package com.CompanyName.SimpleProjectEmployee2.Model;

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
            name="dept_seq",
            sequenceName = "DEPT_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept_seq")
    private Integer deptId;
    private String deptName;

    // One Department -> Many Employees
    @OneToMany(mappedBy = "empDept", cascade = CascadeType.ALL)
    private List<Employee> empList;
}
