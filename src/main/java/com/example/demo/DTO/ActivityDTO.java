package com.example.demo.DTO;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;



public class ActivityDTO {

private Long id;
 @DateTimeFormat(pattern = "yyyy-MM-dd")
private Date date;
private int steps;
private double distance;
private int caloriesBurned;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public ActivityDTO() {
    }

    public ActivityDTO(Long id, Date date, int steps, double distance, int caloriesBurned) {
        this.id = id;
        this.date = date;
        this.steps = steps;
        this.distance = distance;
        this.caloriesBurned = caloriesBurned;
    }

}
