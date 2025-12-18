package com.CompanyName.ConntectingMultipleDB.Repository.UserRepo;

import com.CompanyName.ConntectingMultipleDB.Entity.UserEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//MySQL
public interface UserRepository extends JpaRepository<User, Integer> {
}
