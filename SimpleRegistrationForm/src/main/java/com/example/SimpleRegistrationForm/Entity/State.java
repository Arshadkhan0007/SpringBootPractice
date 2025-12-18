package com.example.SimpleRegistrationForm.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Register_State")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class State {

    @Id
    @SequenceGenerator(name = "STATE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STATE_SEQ")
    private Integer stateId;
    private String stateName;
    @OneToMany(mappedBy = "state", cascade = CascadeType.PERSIST)
    private List<District> districtList;
}
