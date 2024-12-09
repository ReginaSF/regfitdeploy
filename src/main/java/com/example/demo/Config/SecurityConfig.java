package com.example.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

 @Autowired
  

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(authorizeRequests ->
            authorizeRequests
            .requestMatchers("/favicon.ico","/css/**", "/images/**", "/js/**","/register","/home","/error/**", "/login","/logout").permitAll()  
            .requestMatchers("/comingsoon/**","/foodmacros/**","/access-denied/**","/workout/**","/new/**","/activities/**","/workoutlist/**","/calendar/**","/cycle/**").hasAnyRole(  "PREMIUM","BASIC") 
            .requestMatchers("/editPeriodData/**","/deletePeriodData/**", "/periodPhases","/periodForm", "/submitPeriodForm","/submitPeriodData").hasRole("PREMIUM")  // Only accessible by users with role PREMIUM
           )
           .formLogin(form -> form
           .loginPage("/login").permitAll()
           
           .defaultSuccessUrl("/home", true)  // Redirect to /home after successful login
       )    
       
       .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) 
            .permitAll()
        )
   
        .exceptionHandling()
    .accessDeniedPage("/access-denied")  // Set custom access-denied page
.and()
        .csrf(withDefaults());

        return http.build();
  
}

@Bean

public PasswordEncoder passwordEncoder(){

    return new BCryptPasswordEncoder();
}
      @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}