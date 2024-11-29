package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.AppUsers;

public interface AppUserRepository extends JpaRepository<AppUsers, Long> {

    public AppUsers findByEmail(String email);
    boolean existsByEmail(String email);
}
