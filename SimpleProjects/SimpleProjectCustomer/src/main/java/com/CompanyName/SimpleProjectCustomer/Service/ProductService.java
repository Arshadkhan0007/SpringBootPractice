package com.CompanyName.SimpleProjectCustomer.Service;

import com.CompanyName.SimpleProjectCustomer.Dto.ProductDto;
import com.CompanyName.SimpleProjectCustomer.Exception.ResourceNotFoundException;
import com.CompanyName.SimpleProjectCustomer.Mapper.ProductMapper;
import com.CompanyName.SimpleProjectCustomer.Model.Customer;
import com.CompanyName.SimpleProjectCustomer.Model.Product;
import com.CompanyName.SimpleProjectCustomer.Repository.CustomerRepository;
import com.CompanyName.SimpleProjectCustomer.Repository.ProductRepository;
import com.CompanyName.SimpleProjectCustomer.Response.ServiceResponse;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository prodRepo;
    private final ProductMapper prodMapper;

    // Customer Dependency Injection
    public ProductService(ProductRepository prodRepo, ProductMapper prodMapper){
        this.prodRepo = prodRepo;
        this.prodMapper = prodMapper;
    }

    //GET METHODS
    //Get all Products
    public ResponseEntity<ServiceResponse<List<ProductDto> getAllProducts(){
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                "All products retrieved successfully!",
                prodMapper.listToDto(prodRepo.findAll())
        ), HttpStatus.OK);
    }

    //Get product by ID
    public ResponseEntity<ServiceResponse<ProductDto>> getProdById(Integer id){
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                "Successfully retrieved product with id : " + id,
                prodMapper.toDto(prodRepo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Product Id: " + id + " does not exist!")))
        ), HttpStatus.OK);
    }

    //Get products by Name
    public ResponseEntity<ServiceResponse<List<ProductDto>>> getProdByName(String name){
        List<Product> prodList = prodRepo.findByProdName(name);
        if(prodList == null){
            throw new ResourceNotFoundException("Product name : " + name + " does not exist!");
        }
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                "Successfully retrieved product with name : " + name,
                prodMapper.listToDto(prodList)
        ), HttpStatus.OK);
    }

    //Get products whose rating is greater than n
    public ResponseEntity<ServiceResponse<List<ProductDto>>> getProdByRatingGreater(Float n){
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                "Successfully retrieved products whose ratings are greater than " + n,
                prodMapper.listToDto(prodRepo.findByProdRatingGreaterThan(n))
        ), HttpStatus.OK);
    }

    //Get products which are owned by more than n customers
    public ResponseEntity<ServiceResponse<List<ProductDto>>> getProdByNoOfCust(Integer n){
        List<ProductDto> prodDtoList = prodMapper.listToDto(prodRepo.findAll());
        for(int i = 0; i < prodDtoList.size(); i++){
            if(prodDtoList.get(i).getCustIdList().size() < n){
                prodDtoList.remove(i);
            }
        }
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                "Successfully retrieved products which were purchased at Least by " + n + " customer(s)",
                prodDtoList
        ), HttpStatus.OK);
    }

    //POST METHODS
    //Adding a product
    public ResponseEntity<ServiceResponse<ProductDto>> addProduct(ProductDto prodDto){
        Product prod = prodMapper.toModel(prodDto);
        prodDto = prodMapper.toDto(prodRepo.save(prod));
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.CREATED.value(),
                "Product has been added Successfully!",
                prodDto
        ), HttpStatus.CREATED);
    }

    //Adding multiple products
    public ResponseEntity<ServiceResponse<List<ProductDto>>> addProducts(List<ProductDto> prodDtoList){
        List<Product> prodList = prodMapper.listToModel(prodDtoList);
        prodDtoList = prodMapper.listToDto(prodRepo.saveAll(prodList));
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.CREATED.value(),
                prodDtoList.size() + " Product(s) have been added",
                prodDtoList
        ), HttpStatus.CREATED);
    }

    //PUT METHODS
    //Updating a product
    @Transactional
    public ResponseEntity<ServiceResponse<ProductDto>> updateProduct(Integer id, ProductDto updatedProdDto){
        Product updatedProd = prodMapper.toModel(updatedProdDto);
        Product prod = prodRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Id: " + id + " does not exist!"));
        prod.setProdName(updatedProd.getProdName());
        prod.setDescription(updatedProd.getDescription());
        prod.setProdRating(updatedProdDto.getProdRating());
        prod.setCustList(updatedProd.getCustList());

        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                "Product with Id : " + id + " has been updated",
                prodMapper.toDto(prod)
        ), HttpStatus.OK);
    }

    //DELETE METHODS
    //Deleting a product
    @Transactional
    public ResponseEntity<ServiceResponse<ProductDto>> deleteProduct(Integer id){
        Product prod = prodRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Id: " + id + " does not exist!"));
        prodRepo.deleteById(id);

        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                "Product with id : " + id + " has been removed!",
                prodMapper.toDto(prod)
        ), HttpStatus.OK);
    }

//    //HELPER METHODS
//    public Product toModel(ProductDto prodDto){
//        Product prod = new Product();
//        prod.setProdId(prodDto.getProdId());
//        prod.setProdName(prodDto.getProdName());
//        prod.setProdRating(prod.getProdRating());
//        prod.setDescription(prod.getDescription());
//
//        if(prodDto.getCustIdList() != null){
//            List<Customer> custList = new ArrayList<>();
//            for(Integer id : prodDto.getCustIdList()){
//                custList.add(custRepo.findById(id)
//                        .orElseThrow(() -> new ResourceNotFoundException("Customer Id: " + id + " does not exist!")));
//            }
//            prod.setCustList(custList);
//        }
//
//        return prod;
//    }
//
//    public List<Product> listToModel(List<ProductDto> prodDtoList){
//        List<Product> prodList = new ArrayList<>();
//        for(ProductDto prodDto : prodDtoList){
//            Product prod = new Product();
//            prod.setProdId(prodDto.getProdId());
//            prod.setProdName(prodDto.getProdName());
//            prod.setProdRating(prod.getProdRating());
//            prod.setDescription(prod.getDescription());
//
//            if(prodDto.getCustIdList() != null){
//                List<Customer> custList = new ArrayList<>();
//                for(Integer id : prodDto.getCustIdList()){
//                    custList.add(custRepo.findById(id)
//                            .orElseThrow(() -> new ResourceNotFoundException("Customer Id: " + id + " does not exist!")));
//                }
//                prod.setCustList(custList);
//            }
//        }
//        return prodList;
//    }
//
//    public ProductDto toProdDto(Product prod){
//        ProductDto prodDto = new ProductDto();
//        prodDto.setProdId(prod.getProdId());
//        prodDto.setProdName(prod.getProdName());
//        prodDto.setProdRating(prod.getProdRating());
//        prodDto.setDescription(prod.getDescription());
//
//        if(prod.getCustList() != null){
//            Map<Integer,String> custIdMap = new HashMap<>();
//            for(Customer cust : prod.getCustList()) {
//                custIdMap.put(cust.getCustId(), cust.getCustName());
//            }
//        }
//
//        return prodDto;
//    }
//
//    public List<ProductDto> listToProdDto(List<Product> prodList){
//        List<ProductDto> prodDtoList = new ArrayList<>();
//        for(Product prod : prodList){
//            ProductDto prodDto = new ProductDto();
//            prodDto.setProdId(prod.getProdId());
//            prodDto.setProdName(prod.getProdName());
//            prodDto.setProdRating(prod.getProdRating());
//            prodDto.setDescription(prod.getDescription());
//
//            if(prod.getCustList() != null){
//                Map<Integer,String> custIdMap = new HashMap<>();
//                for(Customer cust : prod.getCustList()) {
//                    custIdMap.put(cust.getCustId(), cust.getCustName());
//                }
//            }
//
//            prodDtoList.add(prodDto);
//        }
//        return prodDtoList;
//    }
}
