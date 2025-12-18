package com.CompanyName.PracticeProjectCustomer.Repository;

import com.CompanyName.PracticeProjectCustomer.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
