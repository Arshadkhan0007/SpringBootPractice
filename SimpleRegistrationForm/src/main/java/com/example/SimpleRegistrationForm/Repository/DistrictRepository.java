package com.example.SimpleRegistrationForm.Repository;

import com.example.SimpleRegistrationForm.Entity.District;
import com.example.SimpleRegistrationForm.Entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    Optional<District> findByDistrictName(String districtName);
    Optional<List<District>> findByState_StateId(int stateId);
}
