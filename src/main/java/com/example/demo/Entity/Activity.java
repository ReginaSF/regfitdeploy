package com.example.demo.Entity;

import java.util.Date;

import com.example.demo.DTO.ActivityDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity

public class Activity {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
private Date date;
private int steps;
private double distance;
private int caloriesBurned;


public ActivityDTO getActivityDTO(){

    ActivityDTO activityDTO = new ActivityDTO();

    activityDTO.setId(id);
    activityDTO.setDate(date);
    activityDTO.setDistance(distance);
    activityDTO.setSteps(steps);
    activityDTO.setCaloriesBurned(caloriesBurned);
    
    return activityDTO;
}

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

   
}
