package com.CompanyName.SimpleProjectEmployee2.Repository;

import com.CompanyName.SimpleProjectEmployee2.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public List<Employee> findByEmpName(String empName);
}
