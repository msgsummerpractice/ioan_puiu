package com.example.DBdemo.Service;

import com.example.DBdemo.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

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

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() 
        -> new RuntimeException("User not found"));

        user.setFirstname(userDetails.getFirstname());
        user.setLastname(userDetails.getLastname());
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }

    public User partialUpdateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() 
        -> new RuntimeException("User not found"));

        if (userDetails.getFirstname() != null) {
            user.setFirstname(userDetails.getFirstname());
        }
        if (userDetails.getLastname() != null) {
            user.setLastname(userDetails.getLastname());
        }
        if (userDetails.getUsername() != null)
        {
            user.setUsername(userDetails.getUsername());
        }
        if (userDetails.getEmail() != null)
        {
            user.setEmail(userDetails.getEmail());
        }
        if (userDetails.getPassword() != null)
        {
            user.setPassword(userDetails.getPassword());
        }

        return userRepository.save(user);

    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User removeUser(User user) {
        userRepository.delete(user);
        return user;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> searchUsersByUsername(String username) {
        return userRepository.findTop10ByUsernameContainingIgnoreCaseOrderByUsernameAsc(username);
    }

    public Integer countUsers() {
        return userRepository.countUsers();
    }

}
