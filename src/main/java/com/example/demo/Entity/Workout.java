package com.example.demo.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "workout2")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int totalSets;
    private int totalReps;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Exercise> exercises;

    public Workout() {
    }

    public Workout(Long id, String name, int totalSets, int totalReps, List<Exercise> exercises) {
        this.id = id;
        this.name = name;
        this.totalSets = totalSets;
        this.totalReps = totalReps;
        this.exercises = exercises;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalSets() {
        return totalSets;
    }

    public void setTotalSets(int totalSets) {
        this.totalSets = totalSets;
    }

    public int getTotalReps() {
        return totalReps;
    }

    public void setTotalReps(int totalReps) {
        this.totalReps = totalReps;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    } 
    
}
