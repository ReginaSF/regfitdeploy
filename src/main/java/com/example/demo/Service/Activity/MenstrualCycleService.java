package com.example.demo.Service.Activity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.MenstrualCycle;
import com.example.demo.Repository.MenstrualCycleRepository;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

@Service
public class MenstrualCycleService {

    private final MenstrualCycleRepository menstrualCycleRepository;

    @Autowired
    public MenstrualCycleService(MenstrualCycleRepository menstrualCycleRepository) {
        this.menstrualCycleRepository = menstrualCycleRepository;
    }

    // Save a new menstrual cycle and call the API
    public MenstrualCycle saveMenstrualCycle(MenstrualCycle menstrualCycle) {
        // First, save the menstrual cycle to the database
        MenstrualCycle savedCycle = menstrualCycleRepository.save(menstrualCycle);

        // Prepare the API request body
        JSONObject body = new JSONObject();
        body.put("current_date", menstrualCycle.getCycleStartDate().toString()); 
        body.put("past_cycle_data", new JSONObject[]{
            new JSONObject().put("cycle_start_date", menstrualCycle.getCycleStartDate().toString())
            .put("period_length", menstrualCycle.getPeriodLength())
        });
        body.put("max_cycle_predictions", 1); 

        try {
            // Make the API call
            HttpResponse<String> response = Unirest.post("https://womens-health-menstrual-cycle-phase-predictions-insights.p.rapidapi.com/process_cycle_data")
                    .header("x-rapidapi-key", "c82fa910bemsh5ede39737835b07p1bf816jsn52a57457493b") // Your actual API key
                    .header("x-rapidapi-host", "womens-health-menstrual-cycle-phase-predictions-insights.p.rapidapi.com")
                    .header("Content-Type", "application/json")
                    .body(body.toString())
                    .asString();

            // Log or handle the response from the API
            if (response.getStatus() == 200) {
                // Process the response and update the saved cycle with any new data
                System.out.println("API Response: " + response.getBody());
            } else {
                System.out.println("API Error: " + response.getStatus() + " - " + response.getBody());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return savedCycle;
    }

    // Get all menstrual cycles per user
    public List<MenstrualCycle> getCyclesByUserId(Long userId) {
        return menstrualCycleRepository.findByUserId(userId);
    }

    // Get a menstrual cycle by its cycle ID
    public Optional<MenstrualCycle> getCycleById(Long cycleId) {
        return menstrualCycleRepository.findById(cycleId);
    }

    // Update a menstrual cycle
    public MenstrualCycle updateMenstrualCycle(Long cycleId, MenstrualCycle menstrualCycle) {
        if (menstrualCycleRepository.existsById(cycleId)) {
            menstrualCycle.setCycleId(cycleId);
            return menstrualCycleRepository.save(menstrualCycle);
        }
        return null;
    }

    // Delete a menstrual cycle
    public void deleteMenstrualCycle(Long cycleId) {
        menstrualCycleRepository.deleteById(cycleId);
    }
}
