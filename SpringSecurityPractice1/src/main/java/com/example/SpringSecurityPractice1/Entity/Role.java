package com.example.SpringSecurityPractice1.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @SequenceGenerator(name = "roleSeq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSeq")
    private Integer roleId;
    private String roleName;

}
