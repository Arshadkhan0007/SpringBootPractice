package com.CompanyName.StudentDepartment.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @SequenceGenerator(
            name = "studentSeq",
            sequenceName = "STUDENT_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentSeq")
    private Integer studentId;
    private String studentName;
    private String studentEmail;
    private Long studentNumber;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dept_id")
    @JsonBackReference
    private Department department;
}
