package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entities.User;
import com.example.demo.services.UserServices;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserServices services;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model) {
        
        if (result.hasErrors()) {
            return "register";
        }
        
        if (services.emailExists(user.getEmail())) {
            model.addAttribute("error", "Email already exists");
            return "register";
        }
        
        services.addUser(user);
        return "redirect:/login?registrationSuccess";
    }

    // Removed the conflicting /admin/users mapping
}