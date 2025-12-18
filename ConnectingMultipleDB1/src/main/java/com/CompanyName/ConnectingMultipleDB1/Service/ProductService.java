package com.CompanyName.ConnectingMultipleDB1.Service;

import com.CompanyName.ConnectingMultipleDB1.ProductDatabase.Model.Product;
import com.CompanyName.ConnectingMultipleDB1.ProductDatabase.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public void saveAllProducts(List<Product> productList){
        repo.saveAll(productList);
        System.out.println(productList.size() + " products have been added");
    }

    public void displayAllProducts(){
        System.out.println(repo.findAll());
    }
}
