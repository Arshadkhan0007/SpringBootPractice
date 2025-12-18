package com.CompanyName.ConntectingMultipleDB.Repository.ProductRepo;

import com.CompanyName.ConntectingMultipleDB.Entity.ProductEntity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//Oracle DB

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
