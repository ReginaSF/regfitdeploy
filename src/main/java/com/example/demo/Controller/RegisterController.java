package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.DTO.RegisterDTO;
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
    public String registerUser(@Valid @ModelAttribute("registerDTO") RegisterDTO registerDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register"; 
        }
       
        registerUserService.registerUser(registerDTO);
        model.addAttribute("registerDTO", new RegisterDTO());
        model.addAttribute("successMessage", true);
        return "register"; 
    }
    



}
