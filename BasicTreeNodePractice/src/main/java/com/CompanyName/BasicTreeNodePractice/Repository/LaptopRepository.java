package com.CompanyName.BasicTreeNodePractice.Repository;

import com.CompanyName.BasicTreeNodePractice.Entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Integer> {
    public Laptop findByLaptopName(String laptopName);
}
