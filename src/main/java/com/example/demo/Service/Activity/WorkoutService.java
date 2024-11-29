package com.example.demo.Service.Activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AppUsers;
import com.example.demo.Entity.Exercise;
import com.example.demo.Entity.Workout;
import com.example.demo.Repository.AppUserRepository;
import com.example.demo.Repository.WorkoutRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private AppUserRepository appUsersRepository;  // This injection is intended to fecth my users

    @PersistenceContext
    private EntityManager entityManager;  // Injecting Entitymanager to handle crud operations and users

    public Workout createWorkout(Long userId, List<Long> exerciseIds, Integer sets, Integer reps) {
        // Fetch the user from the database using the userId
        AppUsers user = appUsersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Manually fetch the exercises using the EntityManager
        List<Exercise> exercises = entityManager.createQuery(
                "SELECT e FROM Exercise e WHERE e.id IN :ids", Exercise.class)
                .setParameter("ids", exerciseIds)
                .getResultList();

        // Create a new workout for this user with the selected exercises
        Workout workout = new Workout();
        workout.setUser(user);  
        workout.setExercises(exercises);  
        workout.setSets(sets);
        workout.setReps(reps);

        return workoutRepository.save(workout);
    }

    public List<Workout> getWorkoutsByUser(Long userId) {
        return workoutRepository.findByUserId(userId);  
    }
}
