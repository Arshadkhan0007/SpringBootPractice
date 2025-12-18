package com.CompanyName.PracticeProjectCustomer;

import com.CompanyName.PracticeProjectCustomer.Dto.CustomerRequestDto;
import com.CompanyName.PracticeProjectCustomer.Dto.CustomerResponseDto;
import com.CompanyName.PracticeProjectCustomer.Exception.ResourceNotFoundException;
import com.CompanyName.PracticeProjectCustomer.MapperUtil.ObjectMapperUtil;
import com.CompanyName.PracticeProjectCustomer.Model.Customer;
import com.CompanyName.PracticeProjectCustomer.Model.Product;
import com.CompanyName.PracticeProjectCustomer.Repository.CustomerRepository;
import com.CompanyName.PracticeProjectCustomer.Repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PracticeProjectCustomerApplication implements CommandLineRunner {

    private final ProductRepository prodRepo;
    private final CustomerRepository customerRepo;
    private final ObjectMapperUtil mapperUtil;
    private final ObjectMapper mapper;

    public PracticeProjectCustomerApplication(ProductRepository prodRepo, CustomerRepository customerRepo, ObjectMapperUtil mapperUtil, ObjectMapper mapper) {
        this.prodRepo = prodRepo;
        this.customerRepo = customerRepo;
        this.mapperUtil = mapperUtil;
        this.mapper = mapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(PracticeProjectCustomerApplication.class, args);
	}

    @Transactional
    public void run(String... args) {
        List<Product> productList = Arrays.asList(
                new Product(null, "Monitor", 150000, null),
                new Product(null, "Keyboard", 30000, null),
                new Product(null, "CPU", 350000, null),
                new Product(null, "Mouse", 3000, null),
                new Product(null, "Headset", 50000, null));
        System.out.println(prodRepo.saveAll(productList));
        System.out.println("Added Successfully!!");

        CustomerResponseDto customerResponseDto = mapperUtil.toDto(customerRepo.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Id: " + 1 + " does not exists!")));

        //Object To json String
        String json;
        try {
            System.out.println("Java Object -> Json String (using writeValueAsString() method)");
            json = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(customerResponseDto);
            System.out.println(json);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException();
        }

        //Json String to Object
        try{
            System.out.println("Json String to Object");
            System.out.println(mapper.readValue(json, CustomerResponseDto.class));
        } catch (JsonProcessingException ex){
            throw new RuntimeException();
        }

        // Object -> File
        File customerResponseFile = new File("customerResponse.json");
        try {
            mapper.writeValue(customerResponseFile, customerResponseDto);
            System.out.println("Java object to file (customer.json)");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //File -> Object
        try {
            CustomerResponseDto customerResponseDto1 = mapper.readValue(new File("customerResponse.json"), CustomerResponseDto.class);
            System.out.println("File to Java Object \n" + customerResponseDto1);
        } catch (IOException ex) {
            throw new RuntimeException();
        }

    }

}
