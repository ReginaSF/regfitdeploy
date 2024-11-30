package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.DTO.RegisterDTO;
import com.example.demo.Entity.AppUsers;
import com.example.demo.Repository.AppUserRepository;
import com.example.demo.Service.Activity.RegisterUserService;

import jakarta.validation.Valid;

@Controller
public class RegisterController {
      
        @Autowired
        private RegisterUserService registerUserService;
         @Autowired
    private AppUserRepository appUserRepository;
        
    @GetMapping("/register")
    public String register(Model model) {
      
        RegisterDTO registerDTO = new RegisterDTO();
        model.addAttribute("registerDTO", registerDTO);
        return "register";
    }



    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("registerDTO") RegisterDTO registerDTO, 
                               BindingResult result, Model model) {
        // Check if passwords match
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            result.addError(new FieldError("registerDTO", "confirmPassword", "Passwords do not match!"));
        }
    
        // Check if the email already exists in the database
        AppUsers appUser = appUserRepository.findByEmail(registerDTO.getEmail());
        if (appUser != null) {
            result.addError(new FieldError("registerDTO", "email", "This email is joined already to RegFit!"));
        }
    
        // If there are errors, stay on the registration page
        if (result.hasErrors()) {
            return "register"; 
        }
    
        // If no errors, register the user
        registerUserService.registerUser(registerDTO);
        model.addAttribute("registerDTO", new RegisterDTO());
        model.addAttribute("successMessage", true);
        return "register"; 
    }
    




}
