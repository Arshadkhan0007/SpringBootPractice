package com.example.SimpleRegistrationForm.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Register_District")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class District {

    @Id
    @SequenceGenerator(name = "DISTRICT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISTRICT_SEQ")
    private Integer districtId;
    private String districtName;
    @ManyToOne
    @JoinColumn(name = "STATE_ID")
    private State state;

}