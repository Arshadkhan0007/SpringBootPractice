package com.CompanyName.BasicTreeNodePractice.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDto {
    @NotBlank
    private String studentName;

    @NotBlank
    private String laptopName;
}
