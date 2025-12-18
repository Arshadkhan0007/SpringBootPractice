package com.CompanyName.SimpleProjectStudent.Model;

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
            name = "stud_seq",
            sequenceName = "STUD_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stud_seq")
    private Integer studId;
    private String studName;
    private String studDept;
    private Long studNum;
    private String studEmail;
}
