package com.example.SimpleRegistrationForm.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateResponseDto {

    private Integer stateId;
    private String stateName;

}
