package com.CompanyName.StudentDepartment.Repository;

import com.CompanyName.StudentDepartment.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    public Department findByDepartmentName(String departmentName);
}
