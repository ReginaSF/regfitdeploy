
package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Workout;
import com.example.demo.Service.Activity.WorkoutService;

@RestController
@RequestMapping("/workouts")  
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @PostMapping("/create")
    public ResponseEntity<Workout> createWorkout(@RequestParam Long userId, 
                                                 @RequestParam List<Long> exerciseIds, 
                                                 @RequestParam Integer sets, 
                                                 @RequestParam Integer reps) {
        // Calling the service method to create a workout
        Workout newWorkout = workoutService.createWorkout(userId, exerciseIds, sets, reps);
        return ResponseEntity.ok(newWorkout);  // Return the created workout in the response
    }

    // Endpoint to get all workouts for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Workout>> getWorkoutsByUser(@PathVariable Long userId) {
        List<Workout> workouts = workoutService.getWorkoutsByUser(userId);
        if (workouts.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(workouts);  
    }
}
