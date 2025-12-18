package com.CompanyName.SimpleProjectCustomer.Mapper;

import com.CompanyName.SimpleProjectCustomer.Dto.*;
import com.CompanyName.SimpleProjectCustomer.Exception.ResourceNotFoundException;
import com.CompanyName.SimpleProjectCustomer.Model.Customer;
import com.CompanyName.SimpleProjectCustomer.Model.Product;
import com.CompanyName.SimpleProjectCustomer.Repository.CustomerRepository;
import com.CompanyName.SimpleProjectCustomer.Repository.ProductRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    protected final CustomerRepository custRepo;
    protected final ProductRepository prodRepo;

    protected ProductMapper(CustomerRepository custRepo, ProductRepository prodRepo) {
        this.custRepo = custRepo;
        this.prodRepo = prodRepo;
    }

    @Mapping(target = "custList", expression = "java(toCustList(prodDto.getCustIdMap()))")
    public abstract Product requestToModel(ProductRequestDto prodDto);

    public abstract CustomerResponseDto modelToResponse(Product prod);

    public abstract List<Product> requestListToModel(List<ProductRequestDto> prodDtoList);

    public abstract List<CustomerResponseDto> modelListToResponse(List<Product> prodList);

    protected List<Customer> toCustList(Map<Integer, String> prodIdMap){
        if(prodIdMap == null){
            return null;
        }
        return prodIdMap.keySet().stream()
                .map(id -> custRepo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Customer Id : " + id + " does not exist!")))
                .toList();
    }
}
