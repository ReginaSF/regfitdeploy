package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Workout;
@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    // Custom queries can be added here if needed
}



