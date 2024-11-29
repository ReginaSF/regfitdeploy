package com.example.demo.Service.Activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.RegisterDTO;
import com.example.demo.Entity.AppUsers;
import com.example.demo.Repository.AppUserRepository;

@Service
public class RegisterUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  

    public void registerUser(RegisterDTO registerDTO) {
        // verifying if the user exists already
        if (appUserRepository.existsByEmail(registerDTO.getEmail())) {
            throw new RuntimeException("User already exists.");
        }

        // Creating the new user
        AppUsers appUser = new AppUsers();
        appUser.setEmail(registerDTO.getEmail());
        appUser.setFirstName(registerDTO.getFirstName());
        appUser.setLastName(registerDTO.getLastName());
        appUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));  // Encripta la contraseña
        appUser.setRole(registerDTO.getRole());  // Asigna el rol

        // save the new user
        appUserRepository.save(appUser);
    }
}
