package com.CompanyName.SimpleProjectCustomer.Repository;

import com.CompanyName.SimpleProjectCustomer.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public List<Product> findByProdName(String prodName);
    public List<Product> findByProdRatingGreaterThan(Float prodRating);
}
