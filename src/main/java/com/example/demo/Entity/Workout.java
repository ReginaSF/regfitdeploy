package com.example.demo.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "workoutsfinal")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // This corresponds to the # column in the front-end

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUsers user; // This associates the workout with a specific user

    @ManyToMany
    @JoinTable(
        name = "workout_exercise", // name of the join table
        joinColumns = @JoinColumn(name = "workout_id"), // FK column for Workout
        inverseJoinColumns = @JoinColumn(name = "exercise_id") // FK column for Exercise
    )
    private List<Exercise> exercises; // This associates multiple exercises with a workout

    private LocalDate workoutDate; // Optional: can be used if you want to track the date of the workout

    private Integer sets;  // Number of sets the user performed for the exercise
    private Integer reps;  // Number of reps the user performed for each set

    public Workout() {
    }

    public Workout(List<Exercise> exercises, Long id, Integer reps, Integer sets, AppUsers user, LocalDate workoutDate) {
        this.exercises = exercises;
        this.id = id;
        this.reps = reps;
        this.sets = sets;
        this.user = user;
        this.workoutDate = workoutDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUsers getUser() {
        return user;
    }

    public void setUser(AppUsers user) {
        this.user = user;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(LocalDate workoutDate) {
        this.workoutDate = workoutDate;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }
}
