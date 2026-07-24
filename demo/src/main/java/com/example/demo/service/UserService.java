package com.example.demo.service;

import com.example.demo.repository.UserRepository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public class UserService {
    

    private UserRepository userRepository;

    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
    
    
    



}
