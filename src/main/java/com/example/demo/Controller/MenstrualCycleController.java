package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.AppUsers;
import com.example.demo.Entity.MenstrualCycle;
import com.example.demo.Repository.AppUserRepository;
import com.example.demo.Service.Activity.AppUserService;
import com.example.demo.Service.Activity.MenstrualCycleService;

@Controller
@RequestMapping("/Cycle")  
public class MenstrualCycleController {

    private final MenstrualCycleService menstrualCycleService;
    private final AppUserService userService;
    private final AppUserRepository repo;

    @Autowired
    public MenstrualCycleController(MenstrualCycleService menstrualCycleService, AppUserService userService) {
        this.menstrualCycleService = menstrualCycleService;
        this.userService = userService;
        this.repo = null;
       
    }

    @PostMapping("/savecycle")
    public String createCycle(@ModelAttribute MenstrualCycle menstrualCycle) {
        // Get the currently authenticated user
        String email = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        // Use the AppUserService to load the user details
        AppUsers appUser = repo.findByEmail(email);

        // Associate the user with the menstrual cycle
        menstrualCycle.setUser(appUser);

        // Save the menstrual cycle
        menstrualCycleService.saveMenstrualCycle(menstrualCycle);
        return "redirect:/Cycle/list";  // Redirect to the list page after saving
    }

    @GetMapping("/list")
    public String getCyclesByUserId(Model model) {
      
        String email = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        AppUsers appUser = repo.findByEmail(email);

        List<MenstrualCycle> cycles = menstrualCycleService.getCyclesByUserId(appUser.getId());

        model.addAttribute("cycles", cycles);
        return "cycleList";  
    }

   
    @GetMapping("/add")
    public String showCycleForm(Model model) {
        model.addAttribute("menstrualCycle", new MenstrualCycle());
        return "cycleForm";  
    }
}