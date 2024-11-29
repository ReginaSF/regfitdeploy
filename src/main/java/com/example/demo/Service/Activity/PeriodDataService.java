package com.example.demo.Service.Activity;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AppUsers;
import com.example.demo.Entity.PeriodData;
import com.example.demo.Repository.PeriodDataRepository;
@Service
public class PeriodDataService {

    @Autowired
    private PeriodDataRepository periodDataRepository;

    public PeriodData calculateAndSavePeriodData(PeriodData periodData, AppUsers appUser) {
       // Calculate phases
       LocalDate ovulationStart = periodData.getLastPeriodDate().minusDays(periodData.getPeriodLength()).plusDays(14);

       LocalDate ovulationEnd = ovulationStart.plusDays(1);
       LocalDate lutealPhaseStart = ovulationEnd.plusDays(1);
       LocalDate lutealPhaseEnd = lutealPhaseStart.plusDays(14);

        // Set the calculated phases and user reference
        periodData.setUser(appUser);  // Link the period data to the logged-in user
        periodData.setOvulationStart(ovulationStart);
        periodData.setOvulationEnd(ovulationEnd);
        periodData.setLutealPhaseStart(lutealPhaseStart);
        periodData.setLutealPhaseEnd(lutealPhaseEnd);

        // Save period data to the database
        return periodDataRepository.save(periodData);
    }
}

