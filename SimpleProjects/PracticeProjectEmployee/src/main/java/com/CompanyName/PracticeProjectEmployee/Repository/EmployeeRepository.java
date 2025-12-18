package com.CompanyName.PracticeProjectEmployee.Repository;

import com.CompanyName.PracticeProjectEmployee.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public List<Employee> findByEmpName(String empName);
    public List<Employee> findByDeptName(String deptName);
    public List<Employee> findByEmpSalaryGreaterThan(Integer empSalary);
    public List<Employee> findByEmpSalaryLessThan(Integer empSalary);
    public List<Employee> findByEmpEmailContaining(String keyword);
}
