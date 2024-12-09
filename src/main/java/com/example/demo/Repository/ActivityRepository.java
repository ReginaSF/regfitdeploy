package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Activity;

@Repository

public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
