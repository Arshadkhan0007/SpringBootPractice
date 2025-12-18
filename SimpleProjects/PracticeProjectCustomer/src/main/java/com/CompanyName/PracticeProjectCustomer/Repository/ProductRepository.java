package com.CompanyName.PracticeProjectCustomer.Repository;

import com.CompanyName.PracticeProjectCustomer.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
