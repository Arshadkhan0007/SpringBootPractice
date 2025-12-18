package com.CompanyName.SimpleProjectEmployee2.Dto;

import com.CompanyName.SimpleProjectEmployee2.Model.Employee;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Integer deptId;

    @NotBlank(message = "Department name cannot be blank")
    @Column(unique = true)
    private String deptName;

    private List<Integer> empIdList;
}
