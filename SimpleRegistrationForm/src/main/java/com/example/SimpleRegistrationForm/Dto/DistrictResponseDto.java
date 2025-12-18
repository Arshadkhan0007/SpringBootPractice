package com.example.SimpleRegistrationForm.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictResponseDto {

    private Integer districtId;
    private String districtName;

}
