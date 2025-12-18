package com.CompanyName.SimpleProjectCustomer.Repository;

import com.CompanyName.SimpleProjectCustomer.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public List<Customer> findByCustName(String name);
    public List<Customer> findByPrimeMemberTrue();
    public List<Customer> findByPrimeMemberFalse();
}
