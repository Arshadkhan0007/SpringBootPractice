package com.CompanyName.ConnectingMultipleDB1.UserDatabase.Repository;

import com.CompanyName.ConnectingMultipleDB1.UserDatabase.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
