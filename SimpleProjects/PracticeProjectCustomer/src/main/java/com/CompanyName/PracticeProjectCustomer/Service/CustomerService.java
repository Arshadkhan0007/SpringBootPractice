package com.CompanyName.PracticeProjectCustomer.Service;

import com.CompanyName.PracticeProjectCustomer.Dto.CustomerRequestDto;
import com.CompanyName.PracticeProjectCustomer.Dto.CustomerResponseDto;
import com.CompanyName.PracticeProjectCustomer.Exception.ResourceNotFoundException;
import com.CompanyName.PracticeProjectCustomer.MapperUtil.ObjectMapperUtil;
import com.CompanyName.PracticeProjectCustomer.Model.Customer;
import com.CompanyName.PracticeProjectCustomer.Repository.CustomerRepository;
import com.CompanyName.PracticeProjectCustomer.Response.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repo;
    private final ObjectMapperUtil mapper;

    public CustomerService(CustomerRepository repo, ObjectMapperUtil mapper){
        this.repo = repo;
        this.mapper = mapper;
    }

    //POST METHODS
    //Adding a customer
    public ResponseEntity<SuccessResponse<CustomerResponseDto>> addCustomer(CustomerRequestDto customerRequestDto){
        Customer customer = mapper.toModel(customerRequestDto);

        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.CREATED.value(),
                "Successfully added 1 customer",
                mapper.toDto(repo.save(customer))
        ), HttpStatus.CREATED);
    }

    //Adding multiple customers
    public ResponseEntity<SuccessResponse<List<CustomerResponseDto>>> addAllCustomer(List<CustomerRequestDto> customerRequestDtoList){
        List<Customer> customerList = mapper.listToModel(customerRequestDtoList);
        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.CREATED.value(),
                "Successfully added" + customerList.size() + " customer(s)",
                mapper.listToDto(repo.saveAll(customerList))
        ), HttpStatus.CREATED);
    }

    //GET OPERATIONS
    //Retrieving customer by id
    public ResponseEntity<SuccessResponse<CustomerResponseDto>> findCustomerById(Integer id){
        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.OK.value(),
                "Successfully retrieved customer with ID: " + id,
                mapper.toDto(repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Customer ID: " + id + " does not exist!")))
        ), HttpStatus.OK);
    }

    //Retrieving all customers
    public ResponseEntity<SuccessResponse<List<CustomerResponseDto>>> findAllCustomers(){
        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.OK.value(),
                "Successfully retrieved all customers",
                mapper.listToDto(repo.findAll())
        ), HttpStatus.OK);
    }

    //PUT OPERATION
    //Updating a customer
    public ResponseEntity<SuccessResponse<CustomerResponseDto>> updateCustomer(Integer id, CustomerRequestDto updatedCustomerRequestDto){
        Customer customer = repo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Customer ID: " + id + " does not exist!"));
        Customer updatedCustomer = mapper.toModel(updatedCustomerRequestDto);
        customer.setCustomerName(updatedCustomer.getCustomerName());
        customer.setProductList(updatedCustomer.getProductList());
        customer.setCustomerEmail(updatedCustomer.getCustomerEmail());
        customer.setCustomerUsername(updatedCustomer.getCustomerUsername());
        customer.setCustomerPassword(updatedCustomer.getCustomerPassword());

        repo.save(customer);

        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.OK.value(),
                "Successfully updated customer with ID: " + id,
                mapper.toDto(customer)
        ), HttpStatus.OK);
    }

    //DELETE OPERATIONS
    //Deleting a customer
    public ResponseEntity<SuccessResponse<String>> deleteCustomer(Integer id){
        repo.deleteById(id);
        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.OK.value(),
                "Successfully deleted customer with ID: " + id,
                "Customer with ID:" + id + " has been deleted!"
        ), HttpStatus.OK);
    }
}
