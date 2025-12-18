package com.CompanyName.BasicTreeNodePractice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "laptop_id")
    private Laptop laptop;
}
