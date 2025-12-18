package com.CompanyName.ConnectingMultipleDB1.ProductDatabase.Repository;

import com.CompanyName.ConnectingMultipleDB1.ProductDatabase.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
