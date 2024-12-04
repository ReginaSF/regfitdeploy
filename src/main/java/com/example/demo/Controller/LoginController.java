package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

 
    
    @GetMapping("/login")
    public String login() {
        // Do something with 'param'
        return "login";
    }

   
}