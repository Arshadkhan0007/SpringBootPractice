package com.CompanyName.BasicTreeNodePractice.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaptopRequestDto {
    @NotBlank
    private String laptopName;

    private List<Integer> studentIdList;
}
