package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.AppUsers;
import com.example.demo.Entity.PeriodData;

public interface PeriodDataRepository extends JpaRepository<PeriodData, Long> {
  PeriodData findTopByUserOrderByPeriodIdDesc(AppUsers user);
}
