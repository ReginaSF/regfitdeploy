package com.example.demo.Service.Activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Workout;
import com.example.demo.Repository.WorkoutRepository;
@Service
public class WorkoutService  {

    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public List<Workout> getSavedWorkouts() {
        return workoutRepository.findAll();  // This will return all saved workouts
    }
}
