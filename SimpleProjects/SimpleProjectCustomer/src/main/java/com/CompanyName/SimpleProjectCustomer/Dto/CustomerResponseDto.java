package com.CompanyName.SimpleProjectCustomer.Dto;

import com.CompanyName.SimpleProjectCustomer.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {

    private String custName;
    private Map<Integer, String> prodIdMap;
}
