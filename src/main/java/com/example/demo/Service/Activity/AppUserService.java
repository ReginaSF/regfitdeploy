package com.example.demo.Service.Activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AppUsers;
import com.example.demo.Repository.AppUserRepository;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUsers appUser = repo.findByEmail(email);

        if (appUser == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        String role = appUser.getRole(); 

        UserDetails springUser = User.withUsername(appUser.getEmail())
                .password(appUser.getPassword())
                .roles(role) //PASSING THE ROLE
                .build();

        return springUser;
    }
}
