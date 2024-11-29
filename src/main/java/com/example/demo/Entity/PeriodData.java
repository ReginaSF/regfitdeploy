package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "period_data")
public class PeriodData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "period_id")
    private Long periodId;

    @Column(name = "last_period_date")
    private LocalDate lastPeriodDate;

    @Column(name = "luteal_phase_end")
    private LocalDate lutealPhaseEnd;

    @Column(name = "luteal_phase_start")
    private LocalDate lutealPhaseStart;

    @Column(name = "ovulation_end")
    private LocalDate ovulationEnd;

    @Column(name = "ovulation_start")
    private LocalDate ovulationStart;

    @Column(name = "period_length")
    private int periodLength;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private AppUsers user;

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

    public AppUsers getUser() {
        return user;
    }

    public void setUser(AppUsers user) {
        this.user = user;
    }
}