package com.CompanyName.SimpleProjectEmployee2.Repository;

import com.CompanyName.SimpleProjectEmployee2.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    public List<Department> findByDeptName(String deptName);
}
