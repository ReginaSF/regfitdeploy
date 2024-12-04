package com.example.demo.Controller;

import com.example.demo.Service.Activity.ExerciseService;
import com.example.demo.Service.Activity.WorkoutService;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.AppUsers;
import com.example.demo.Entity.Exercise;
import com.example.demo.Repository.AppUserRepository; // To fetch users
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private AppUserRepository userRepository;  // Assuming this repository exists for user fetching

    // Search exercises based on primary muscle group
    @GetMapping("/workout")
    public String searchExercises(
            @RequestParam(value = "primaryMuscle", defaultValue = "") String primaryMuscle, 
            Model model) {
        try {
            HttpResponse<String> response;
            // If no muscle is specified, show general exercises
            if (primaryMuscle.isEmpty()) {
                response = exerciseService.getExercisesByMuscle("all");
            } else {
                response = exerciseService.getExercisesByMuscle(primaryMuscle);
            }

            // Unwrapping the JSON response to get exercises
            ObjectMapper objectMapper = new ObjectMapper();
            List<Exercise> exercises = objectMapper.readValue(
                    response.getBody(), new TypeReference<List<Exercise>>() {}
            );

            // Adding the exercises to the model to be displayed
            model.addAttribute("exercises", exercises);
        } catch (UnirestException | JsonProcessingException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error fetching exercises: " + e.getMessage());
        }

        return "workout";
    }

    // Add selected exercise to the user's workout
    @PostMapping("/add-to-workout")
    public String addExerciseToWorkout(@RequestParam Long exerciseId,
                                       @RequestParam Integer sets,
                                       @RequestParam Integer reps,
                                       Principal principal) {
        // Get the logged-in user's username from the Principal object
        String username = principal.getName();
        
        // Fetch the user from the database by email (assuming Principal contains the email)
AppUsers user = userRepository.findByEmail(username);  // `username` here is the user's email
if (user == null) {
    return "error-page";  // Handle if user not found
}

        // Create a new workout for this user with the selected exercise

        // Redirect to the workout list page (or any other page)
        return "redirect:/workoutlist";
    }
}
