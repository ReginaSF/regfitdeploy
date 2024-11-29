package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.FoodMacros;

@Repository
public interface FoodMacrosRepository extends JpaRepository<FoodMacros, Long> {
    // Custom query method to search by name or brand
    FoodMacros findByNameOrBrand(String name, String brand);
}
