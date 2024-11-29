package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.MenstrualCycle;

@Repository
public interface MenstrualCycleRepository extends JpaRepository<MenstrualCycle, Long> {

    // query to fetch all cycles by user ID
    List<MenstrualCycle> findByUserId(Long userId);  
}