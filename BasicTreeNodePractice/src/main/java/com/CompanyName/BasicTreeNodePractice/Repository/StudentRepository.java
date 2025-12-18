package com.CompanyName.BasicTreeNodePractice.Repository;

import com.CompanyName.BasicTreeNodePractice.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
