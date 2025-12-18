package com.CompanyName.SimpleProjectCustomer.Mapper;

import com.CompanyName.SimpleProjectCustomer.Dto.CustomerDto;
import com.CompanyName.SimpleProjectCustomer.Dto.CustomerRequestDto;
import com.CompanyName.SimpleProjectCustomer.Dto.CustomerResponseDto;
import com.CompanyName.SimpleProjectCustomer.Exception.ResourceNotFoundException;
import com.CompanyName.SimpleProjectCustomer.Model.Customer;
import com.CompanyName.SimpleProjectCustomer.Model.Product;
import com.CompanyName.SimpleProjectCustomer.Repository.CustomerRepository;
import com.CompanyName.SimpleProjectCustomer.Repository.ProductRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CustomerMapper {
    private final ProductRepository prodRepo;

    protected CustomerMapper(ProductRepository prodRepo) {
        this.prodRepo = prodRepo;
    }

    @Mapping(target = "prodList", expression = "java(toProdList(requestDto.getProdIdMap()))")
    public abstract Customer requestDtoToModel(CustomerRequestDto requestDto);

    @Mapping(target = "prodIdMap", expression = "java(toProdIdMap(cust.getProdList()))")
    public abstract CustomerResponseDto modelToResponseDto(Customer cust);

    public abstract List<Customer> requestDtoListToModel(List<CustomerRequestDto> requestDtoList);

    public abstract List<CustomerResponseDto> modelListToResponseDto(List<Customer> custList);

    public List<Product> toProdList(Map<Integer, String> prodIdMap){
        if(prodIdMap == null){
            return null;
        }
        return prodIdMap.keySet().stream()
                .map(id -> prodRepo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Product Id: " + id + " does not exist!")))
                .toList();
    }

    public Map<Integer, String> toProdMap(List<Product> prodList){
        if(prodList == null){
            return null;
        }
        return prodList.stream()
                .collect(Collectors.toMap(Product::getProdId, Product::getProdName));
    }
}
