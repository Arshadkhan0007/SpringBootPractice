package com.CompanyName.PracticeProjectCustomer.MapperUtil;

import com.CompanyName.PracticeProjectCustomer.Dto.CustomerRequestDto;
import com.CompanyName.PracticeProjectCustomer.Dto.CustomerResponseDto;
import com.CompanyName.PracticeProjectCustomer.Dto.ProductDto;
import com.CompanyName.PracticeProjectCustomer.Exception.ResourceNotFoundException;
import com.CompanyName.PracticeProjectCustomer.Model.Customer;
import com.CompanyName.PracticeProjectCustomer.Model.Product;
import com.CompanyName.PracticeProjectCustomer.Repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ObjectMapperUtil {
    private final ObjectMapper mapper;
    private final ProductRepository repo;

    public ObjectMapperUtil(ObjectMapper mapper, ProductRepository repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    public CustomerResponseDto toDto(Customer customer){
        CustomerResponseDto customerResponseDto = mapper.convertValue(customer, CustomerResponseDto.class);

        if(customer.getProductList() != null){
            List<ProductDto> productDtoList = new ArrayList<>();
            for(Product product : customer.getProductList()){
                productDtoList.add(mapper.convertValue(product, ProductDto.class));
            }

            customerResponseDto.setProductDtoList(productDtoList);
        }
        return customerResponseDto;
    }

    public List<CustomerResponseDto> listToDto(List<Customer> customerList){
        List<CustomerResponseDto> customerResponseDtoList = new ArrayList<>();
        for(Customer customer : customerList){
            CustomerResponseDto customerResponseDto = mapper.convertValue(customer, CustomerResponseDto.class);

            if(customer.getProductList() != null){
                List<ProductDto> productDtoList = new ArrayList<>();
                for(Product product : customer.getProductList()){
                    productDtoList.add(mapper.convertValue(product, ProductDto.class));
                }

                customerResponseDto.setProductDtoList(productDtoList);

                customerResponseDtoList.add(customerResponseDto);
            }
        }
        return customerResponseDtoList;
    }

    public Customer toModel(CustomerRequestDto customerRequestDto){
        Customer customer = mapper.convertValue(customerRequestDto, Customer.class);

        if(customerRequestDto.getProductIdList() != null){
            List<Product> productList = new ArrayList<>();
            for(Integer productId: customerRequestDto.getProductIdList()){
                Product product = repo.findById(productId).
                        orElseThrow(() -> new ResourceNotFoundException("Product ID: " + productId + " does not exist!"));
                productList.add(product);
            }

            customer.setProductList(productList);
        }

        return customer;
    }

    public List<Customer> listToModel(List<CustomerRequestDto> customerRequestDtoList){

        List<Customer> customerList = new ArrayList<>();
        for(CustomerRequestDto customerRequestDto : customerRequestDtoList){

            Customer customer = mapper.convertValue(customerRequestDto, Customer.class);

            if(customerRequestDto.getProductIdList() != null){
                List<Product> productList = new ArrayList<>();
                for(Integer productId: customerRequestDto.getProductIdList()){
                    Product product = repo.findById(productId).
                            orElseThrow(() -> new ResourceNotFoundException("Product ID: " + productId + " does not exist!"));
                    productList.add(product);
                }

                customer.setProductList(productList);

                customerList.add(customer);
            }
        }
        return customerList;
    }
}
