package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entities.User;
import com.example.demo.services.UserServices;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private UserServices userServices;

    @GetMapping("/users")
    public String userManagement(Model model) {
        model.addAttribute("users", userServices.getAllUsers());
        return "admin/user-management";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user) {
        userServices.addUser(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/update/{id}")
    public String updateUser(@PathVariable("id") int id, @ModelAttribute User user) {
        userServices.updateUser(user, id);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userServices.deleteUser(id);
        return "redirect:/admin/users";
    }
}