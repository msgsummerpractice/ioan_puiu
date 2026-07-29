package com.example.DBdemo.Service;

import com.example.DBdemo.Repository.UserRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.DBdemo.Model.User;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User removeUser(User user) {
        userRepository.delete(user);
        return user;
    }

    public User getUserById(long id) {
        return userRepository.findById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> searchUsersByUsername(String username) {
        return userRepository.findTop10ByUsernameContainingIgnoreCaseOrderByUsernameAsc(username);
    }

    public Integer countUsers() {
        return userRepository.countUsers();
    }

}
