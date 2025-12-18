package com.CompanyName.BasicTreeNodePractice.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Laptop {
    @Id
    @SequenceGenerator(
            name = "courseSeq",
            sequenceName = "COURSE_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courseSeq")
    private Integer laptopId;
    @Column(unique = true)
    private String laptopName;
    @OneToMany(mappedBy = "laptop")
    @JsonIgnore
    List<Student> studentList;
}
