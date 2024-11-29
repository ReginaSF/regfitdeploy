package com.example.demo.Entity;
import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="menstrualcycle")

public class MenstrualCycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cycle_id")
    private Long cycleId;  // Renamed field to cycleId

    @Column(name = "last_period_date", nullable = false)
    private LocalDate lastPeriodDate;

    @Column(name = "cycle_length", nullable = false)
    private int cycleLength;

    @Column(name = "period_length", nullable = false)
    private int periodLength;

    @Column(name = "predicted_next_period_date")
    private LocalDate predictedNextPeriodDate;
    @Column(name = "cycle_start_date")
    private Date cycleStartDate; 

    @Column(name = "predicted_ovulation_date")
    private LocalDate predictedOvulationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private AppUsers user;  // Join with "usersfinal" table's "id" column

    public MenstrualCycle() {
    }

    public MenstrualCycle(Long cycleId, LocalDate lastPeriodDate, int cycleLength, int periodLength,
            LocalDate predictedNextPeriodDate, LocalDate predictedOvulationDate, AppUsers user) {
        this.cycleId = cycleId;
        this.lastPeriodDate = lastPeriodDate;
        this.cycleLength = cycleLength;
        this.periodLength = periodLength;
        this.predictedNextPeriodDate = predictedNextPeriodDate;
        this.predictedOvulationDate = predictedOvulationDate;
        this.user = user;
    }

    public Long getCycleId() {
        return cycleId;
    }

    public void setCycleId(Long cycleId) {
        this.cycleId = cycleId;
    }

    public LocalDate getLastPeriodDate() {
        return lastPeriodDate;
    }

    public void setLastPeriodDate(LocalDate lastPeriodDate) {
        this.lastPeriodDate = lastPeriodDate;
    }

    public int getCycleLength() {
        return cycleLength;
    }

    public void setCycleLength(int cycleLength) {
        this.cycleLength = cycleLength;
    }

    public int getPeriodLength() {
        return periodLength;
    }

    public void setPeriodLength(int periodLength) {
        this.periodLength = periodLength;
    }

    public LocalDate getPredictedNextPeriodDate() {
        return predictedNextPeriodDate;
    }

    public void setPredictedNextPeriodDate(LocalDate predictedNextPeriodDate) {
        this.predictedNextPeriodDate = predictedNextPeriodDate;
    }

    public LocalDate getPredictedOvulationDate() {
        return predictedOvulationDate;
    }

    public void setPredictedOvulationDate(LocalDate predictedOvulationDate) {
        this.predictedOvulationDate = predictedOvulationDate;
    }

    public AppUsers getUser() {
        return user;
    }

    public void setUser(AppUsers user) {
        this.user = user;
    }

    public void setCycleStartDate(Date cycleStartDate) {
        this.cycleStartDate = cycleStartDate;
    }
    public Date getCycleStartDate() {
        return cycleStartDate;
    }

    public MenstrualCycle(Long cycleId, LocalDate lastPeriodDate, int cycleLength, int periodLength,
            LocalDate predictedNextPeriodDate, Date cycleStartDate, LocalDate predictedOvulationDate, AppUsers user) {
        this.cycleId = cycleId;
        this.lastPeriodDate = lastPeriodDate;
        this.cycleLength = cycleLength;
        this.periodLength = periodLength;
        this.predictedNextPeriodDate = predictedNextPeriodDate;
        this.cycleStartDate = cycleStartDate;
        this.predictedOvulationDate = predictedOvulationDate;
        this.user = user;
    }
}

