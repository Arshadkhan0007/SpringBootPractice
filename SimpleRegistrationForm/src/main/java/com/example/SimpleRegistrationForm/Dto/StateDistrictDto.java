package com.example.SimpleRegistrationForm.Dto;

import lombok.Data;

import java.util.List;

@Data
public class StateDistrictDto {
    private String state;
    private List<String> districts;
}
