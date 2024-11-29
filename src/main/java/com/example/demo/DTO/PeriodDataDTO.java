package com.example.demo.DTO;

import java.time.LocalDate;

public class PeriodDataDTO {

    private Long periodId;
    private LocalDate lastPeriodDate;
    private LocalDate lutealPhaseEnd;
    private LocalDate lutealPhaseStart;
    private LocalDate ovulationEnd;
    private LocalDate ovulationStart;
    private int periodLength;
    private Long userId; // To store the user ID instead of the whole AppUsers object

    // Getters and Setters
    public Long getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }

    public LocalDate getLastPeriodDate() {
        return lastPeriodDate;
    }

    public void setLastPeriodDate(LocalDate lastPeriodDate) {
        this.lastPeriodDate = lastPeriodDate;
    }

    public LocalDate getLutealPhaseEnd() {
        return lutealPhaseEnd;
    }

    public void setLutealPhaseEnd(LocalDate lutealPhaseEnd) {
        this.lutealPhaseEnd = lutealPhaseEnd;
    }

    public LocalDate getLutealPhaseStart() {
        return lutealPhaseStart;
    }

    public void setLutealPhaseStart(LocalDate lutealPhaseStart) {
        this.lutealPhaseStart = lutealPhaseStart;
    }

    public LocalDate getOvulationEnd() {
        return ovulationEnd;
    }

    public void setOvulationEnd(LocalDate ovulationEnd) {
        this.ovulationEnd = ovulationEnd;
    }

    public LocalDate getOvulationStart() {
        return ovulationStart;
    }

    public void setOvulationStart(LocalDate ovulationStart) {
        this.ovulationStart = ovulationStart;
    }

    public int getPeriodLength() {
        return periodLength;
    }

    public void setPeriodLength(int periodLength) {
        this.periodLength = periodLength;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
