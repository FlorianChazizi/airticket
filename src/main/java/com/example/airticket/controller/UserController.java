package com.example.airticket.controller;

import com.example.airticket.dto.UserForm;
import com.example.airticket.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("form", new UserForm());
        return "users";  // users.html
    }
    
    @PostMapping
    public String createUser(@Valid UserForm form, Model model) {
        try {
            userService.createUser(form);
            return "redirect:/users";  // Reloads list instantly!
        } catch (Exception e) {
            model.addAttribute("error", "Username already exists!");
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("form", form);
            return "users";
        }
    }
}
