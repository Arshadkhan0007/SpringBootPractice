package com.CompanyName.PracticeProjectCustomer.Controller;

import com.CompanyName.PracticeProjectCustomer.Dto.CustomerRequestDto;
import com.CompanyName.PracticeProjectCustomer.Dto.CustomerResponseDto;
import com.CompanyName.PracticeProjectCustomer.Response.SuccessResponse;
import com.CompanyName.PracticeProjectCustomer.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<String> homePage(){
        return new ResponseEntity<>("Hello and Welcome!! :)", HttpStatus.OK);
    }
    @GetMapping("/customer/{id}")
    public ResponseEntity<SuccessResponse<CustomerResponseDto>> findCustomerById(@PathVariable Integer id){
        return service.findCustomerById(id);
    }
    @GetMapping("/all_customers")
    public ResponseEntity<SuccessResponse<List<CustomerResponseDto>>> findAllCustomers(){
        return service.findAllCustomers();
    }

    @PostMapping("/add_customer")
    public ResponseEntity<SuccessResponse<CustomerResponseDto>> addCustomer(@RequestBody @Valid CustomerRequestDto customerRequestDto){
        return service.addCustomer(customerRequestDto);
    }
    @PostMapping("/add_all_customer")
    public ResponseEntity<SuccessResponse<List<CustomerResponseDto>>> addAllCustomers(@RequestBody @Valid List<CustomerRequestDto> customerRequestDtoList){
        return service.addAllCustomer(customerRequestDtoList);
    }

    @PutMapping("/update_customer/{id}")
    public ResponseEntity<SuccessResponse<CustomerResponseDto>> updateCustomer(@PathVariable Integer id, @RequestBody @Valid CustomerRequestDto updatedCustomerRequestDto){
        return service.updateCustomer(id, updatedCustomerRequestDto);
    }

    @DeleteMapping("/delete_customer/{id}")
    public ResponseEntity<SuccessResponse<String>> deleteCustomer(@PathVariable Integer id){
        return service.deleteCustomer(id);
    }
}
