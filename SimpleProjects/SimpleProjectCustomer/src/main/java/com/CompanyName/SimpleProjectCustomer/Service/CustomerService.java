package com.CompanyName.SimpleProjectCustomer.Service;

import com.CompanyName.SimpleProjectCustomer.Dto.CustomerRequestDto;
import com.CompanyName.SimpleProjectCustomer.Dto.CustomerResponseDto;
import com.CompanyName.SimpleProjectCustomer.Exception.ResourceNotFoundException;
import com.CompanyName.SimpleProjectCustomer.Mapper.CustomerMapper;
import com.CompanyName.SimpleProjectCustomer.Model.Customer;
import com.CompanyName.SimpleProjectCustomer.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repo;
    private final CustomerMapper mapper;

    //Constructor Dependency Injection
    public CustomerService(CustomerRepository repo, CustomerMapper mapper){
        this.repo = repo;
        this.mapper = mapper;
    }

    //GET METHODS
    //Get all customers
    public List<CustomerResponseDto> getAllCustomers(){
        return mapper.modelListToResponseDto(repo.findAll());
    }

    //Get customer by ID
    public CustomerResponseDto getCustById(Integer id){
        return mapper.modelToResponseDto(repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Id : " + id + " does not exist!")));
    }

    //Get customer by Name
    public List<CustomerResponseDto> getCustByName(String name){
        List<CustomerResponseDto> custDtoList = mapper.modelListToResponseDto(repo.findByCustName(name));
        if(custDtoList.isEmpty()){
            throw new ResourceNotFoundException("Customer name: " + name + " does not exist!");
        }
        return custDtoList;
    }

    //Get customers with prime membership
    public List<CustomerResponseDto> getPrimeCustomer(){
        return mapper.modelListToResponseDto(repo.findByPrimeMemberFalse());
    }

    //Get customer without prime membership
    public List<CustomerResponseDto> getNonPrimeCust(){
        return mapper.modelListToResponseDto(repo.findByPrimeMemberFalse());
    }

    //POST METHODS
    //Adding a customer
    public CustomerResponseDto saveCustomer(CustomerRequestDto custDto){
        Customer cust = mapper.requestDtoToModel(custDto);
        return mapper.modelToResponseDto(repo.save(cust));
    }

    //Adding multiple customers
    public List<CustomerResponseDto> saveAllCustomers(List<CustomerRequestDto> custDtoList){
        List<Customer> custList = mapper.requestDtoListToModel(custDtoList);
        return mapper.modelListToResponseDto(repo.saveAll(custList));
    }

    //PUT METHOD
    //Updating a customer
    @Transactional
    public CustomerResponseDto updateCustomer(Integer id, CustomerRequestDto updatedCustDto){
        Customer cust = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Id : " + id + " does not exist!"));
        Customer updatedCust = mapper.requestDtoToModel(updatedCustDto);

        cust.setCustName(updatedCust.getCustName());
        cust.setPrimeMember(updatedCust.getPrimeMember());
        cust.setCustNum(updatedCust.getCustNum());
        cust.setCustEmail(updatedCust.getCustEmail());
        cust.setProdList(updatedCust.getProdList());

        return mapper.modelToResponseDto(cust);
    }

    //DELETE METHOD
    //Deleting a customer
    @Transactional
    public CustomerResponseDto deleteCustomer(Integer id){
        Customer cust = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Id : " + id + " does not exist!"));
        repo.deleteById(id);

        return mapper.modelToResponseDto(cust);
    }

//    //HELPER METHODS
//    public Customer toModel(CustomerDto custDto){
//        Customer cust = new Customer();
//
//        cust.setCustId(custDto.getCustId());
//        cust.setCustName(custDto.getCustName());
//        cust.setPrimeMember(custDto.getPrimeMember());
//        cust.setCustNum(custDto.getCustNum());
//        cust.setCustEmail(custDto.getCustEmail());
//
//        if(custDto.getProdIdMap() != null){
//            List<Product> prodList = new ArrayList<>();
//            for(Integer id : custDto.getProdIdMap().keySet()){
//               prodList.add(prodRepo.findById(id)
//                       .orElseThrow(() -> new ResourceNotFoundException("Product Id: " + id + " does not exist!")));
//            }
//            cust.setProdList(prodList);
//        }
//
//        return cust;
//    }
//
//    public List<Customer> listToModel(List<CustomerDto> custDtoList){
//
//        List<Customer> custList = new ArrayList<>();
//        for(CustomerDto custDto : custDtoList){
//            Customer cust = new Customer();
//
//            cust.setCustId(custDto.getCustId());
//            cust.setCustName(custDto.getCustName());
//            cust.setPrimeMember(custDto.getPrimeMember());
//            cust.setCustNum(custDto.getCustNum());
//            cust.setCustEmail(custDto.getCustEmail());
//
//            if(custDto.getProdIdMap() != null){
//                List<Product> prodList = new ArrayList<>();
//                for(Integer id : custDto.getProdIdMap().keySet()){
//                    prodList.add(prodRepo.findById(id)
//                            .orElseThrow(() -> new ResourceNotFoundException("Product Id: " + id + " does not exist!")));
//                }
//                cust.setProdList(prodList);
//            }
//
//            custList.add(cust);
//        }
//
//        return custList;
//    }
//
//    public CustomerDto toDto(Customer cust){
//        CustomerDto custDto = new CustomerDto();
//
//        custDto.setCustId(cust.getCustId());
//        custDto.setCustName(cust.getCustName());
//        custDto.setPrimeMember(custDto.getPrimeMember());
//        cust.setCustNum(custDto.getCustNum());
//        cust.setCustEmail(custDto.getCustEmail());
//
//        if(cust.getProdList() != null){
//            Map<Integer, String> prodMap = new HashMap<>();
//            for(Product p : cust.getProdList()){
//                prodMap.put(p.getProdId(), p.getProdName());
//            }
//            custDto.setProdIdMap(prodMap);
//        }
//
//        return custDto;
//    }
//
//    public List<CustomerDto> listToDto (List<Customer> custList){
//        List<CustomerDto> custDtoList = new ArrayList<>();
//        for(Customer cust : custList){
//            CustomerDto custDto = new CustomerDto();
//
//            custDto.setCustId(cust.getCustId());
//            custDto.setCustName(cust.getCustName());
//            custDto.setPrimeMember(custDto.getPrimeMember());
//            cust.setCustNum(custDto.getCustNum());
//            cust.setCustEmail(custDto.getCustEmail());
//
//            if(cust.getProdList() != null){
//                Map<Integer, String> prodList = new HashMap<>();
//                for(Product p : cust.getProdList()){
//                    prodList.put(p.getProdId(), p.getProdName());
//                }
//                custDto.setProdIdMap(prodList);
//            }
//        }
//        return custDtoList;
//    }
}
