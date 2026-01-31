package com.example.airticket.service;

import com.example.airticket.dto.UserForm;
import com.example.airticket.entity.User;
import com.example.airticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User createUser(UserForm form) {
        if (userRepository.existsByUsername(form.getUsername())) {
            throw new RuntimeException("Username exists");
        }
        User user = new User();
        user.setUsername(form.getUsername());
        return userRepository.save(user);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAllByOrderByCreatedAtDesc();
    }
}
