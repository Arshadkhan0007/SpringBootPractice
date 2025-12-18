package com.CompanyName.BasicTreeNodePractice.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDto {
    @NotBlank
    private String studentName;

    @NotBlank
    private LaptopResponseDto laptopResponse;
}
