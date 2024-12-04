/* package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Entity.Workout;
import com.example.demo.Service.Activity.WorkoutService;
@Controller
public class WorkoutController {

    // Inject your service or repository for retrieving saved workouts
    @Autowired
    private WorkoutService workoutService;

    @GetMapping("/saved-workouts")
    public String getSavedWorkouts(Model model) {
        // Fetch the saved workouts from the database or repository
        List<Workout> savedWorkouts = workoutService.getSavedWorkouts();

        // Add the workouts to the model
        model.addAttribute("savedWorkouts", savedWorkouts);

        // Return the view name (the HTML page)
        return "workoutlist";  // This should match the name of your Thymeleaf template
    }
}

*/