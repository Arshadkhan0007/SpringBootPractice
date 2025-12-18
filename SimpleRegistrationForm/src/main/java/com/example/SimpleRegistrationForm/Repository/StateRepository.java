package com.example.SimpleRegistrationForm.Repository;

import com.example.SimpleRegistrationForm.Entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {
    Optional<State> findByStateName(String stateName);
}
