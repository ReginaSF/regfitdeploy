package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Entity.AppUsers;
import com.example.demo.Entity.PeriodData;
import com.example.demo.Repository.AppUserRepository;
import com.example.demo.Repository.PeriodDataRepository;
import com.example.demo.Service.Activity.PeriodDataService;

@Controller
public class PeriodDataController {

    @Autowired
    private PeriodDataRepository periodDataRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PeriodDataService periodDataService;
    @GetMapping("/periodForm")
    public String showPeriodForm(Model model) {
        // Get the authenticated user's details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();  // This is Spring's UserDetails
    
        String email = userDetails.getUsername();
        AppUsers appUser = appUserRepository.findByEmail(email);  // Fetching AppUser entity
    
        if (appUser == null) {
            throw new RuntimeException("User not found");
        }
    
        // Create a new PeriodData object and set the AppUser
        PeriodData periodData = new PeriodData();
        periodData.setUser(appUser);  // Associate the PeriodData with the AppUser
    
        model.addAttribute("periodData", periodData);
    
        return "periodForm";  
    }

    @PostMapping("/submitPeriodData")
    public String submitPeriodData(@ModelAttribute("periodData") PeriodData periodData) {
        // Get the authenticated user's details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Use the username (email in this case) to fetch the actual AppUsers entity
        String email = userDetails.getUsername();
        AppUsers appUser = appUserRepository.findByEmail(email);

        if (appUser == null) {
            throw new RuntimeException("User not found");
        }

        //Calculation and saving process
        periodDataService.calculateAndSavePeriodData(periodData, appUser);

        // Redirect to a summary after saving the data
        return "redirect:/periodPhases";
    }

    @GetMapping("/periodPhases")
    public String showPeriodPhases(Model model) {
        // Get the authenticated user's details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Use the email to fetch the actual AppUsers entity
        String email = userDetails.getUsername();
        AppUsers appUser = appUserRepository.findByEmail(email);

        if (appUser == null) {
            throw new RuntimeException("User not found");
        }

        // Fetch the latest PeriodData for the user
        PeriodData periodData = periodDataRepository.findTopByUserOrderByPeriodIdDesc(appUser);

        // Add the PeriodData object to the model
        model.addAttribute("periodData", periodData);

        return "periodPhases"; // shows periodPhases view
    }

    @GetMapping("/editPeriodData/{id}")  // Edit a record on the periods list
    public String editPeriodData(@PathVariable("id") Long id, Model model) {

        // Get the authenticated user's details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String email = userDetails.getUsername();
        AppUsers appUser = appUserRepository.findByEmail(email);

        if (appUser == null) {
            throw new RuntimeException("User not found");
        }

        PeriodData periodData = periodDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PeriodData not found"));

        // Ensure the period data belongs to the current user
        if (!periodData.getUser().equals(appUser)) {
            throw new RuntimeException("Unauthorized access to this data");
        }
        model.addAttribute("periodData", periodData);
        return "periodForm"; // Return the form for editing
    }

    // Delete PeriodData
    @GetMapping("/deletePeriodData/{id}")
    public String deletePeriodData(@PathVariable("id") Long id) {
        // Get the authenticated user's details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String email = userDetails.getUsername();
        AppUsers appUser = appUserRepository.findByEmail(email);

        if (appUser == null) {
            throw new RuntimeException("User not found");
        }

        PeriodData periodData = periodDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PeriodData not found"));

        // Checking the period data belongs to the current user
        if (!periodData.getUser().equals(appUser)) {
            throw new RuntimeException("Unauthorized access to this data");
        }

        periodDataRepository.delete(periodData);
        return "redirect:/periodPhases"; // Redirect after deletion
    }
}
