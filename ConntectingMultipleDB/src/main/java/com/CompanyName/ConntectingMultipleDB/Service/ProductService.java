package com.CompanyName.ConntectingMultipleDB.Service;

import com.CompanyName.ConntectingMultipleDB.Entity.ProductEntity.Product;
import com.CompanyName.ConntectingMultipleDB.Entity.UserEntity.User;
import com.CompanyName.ConntectingMultipleDB.Repository.ProductRepo.ProductRepository;
import com.CompanyName.ConntectingMultipleDB.Repository.UserRepo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository prodRepo;
    private final UserRepository userRepo;


  @Transactional("oracleTransactionManager")
    public void addAllProdData(List<Product> productList){
        System.out.println("-----Saving data into Oracle Database-----");
        prodRepo.saveAll(productList);
        System.out.println(productList.size() + " products saved into oracle database");
    }

    @Transactional("mysqlTransactionManager")
    public void addAllUserData(List<User> UserList){
        System.out.println("-----Saving data into Oracle Database-----");
        userRepo.saveAll(UserList);
        System.out.println(UserList.size() + " Users saved into oracle database");
    }

    public void displayData(){
        System.out.println("-----Fetching data from Oracle Database");
        prodRepo.findAll().forEach(product -> System.out.println(product));
        userRepo.findAll().forEach(user -> System.out.println(user));
    }
}
