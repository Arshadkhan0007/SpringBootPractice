package com.CompanyName.SimplePracticeStuff.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @SequenceGenerator(
            name = "personSeq",
            sequenceName = "PERSON_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PersonSeq")
    private Integer Id;
    private String name;
    private String email;
}
