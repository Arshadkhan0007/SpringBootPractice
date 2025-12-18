package com.example.ProductsMicroService.repository;

import com.example.ProductsMicroService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer>{
    Product findByName(String name);
   // boolean existByName(String name);

}
