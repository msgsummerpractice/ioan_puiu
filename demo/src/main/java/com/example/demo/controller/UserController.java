package com.example.demo.controller;



import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.ControllerConfig;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ControllerConfig controllerConfig;

    @Value("${log.text}")
    private String logSufix;
    
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class); 

    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/all")
    public List<User> getAllUsers() {
        logger.info("Fetching all users {} {}", controllerConfig.getLogText(), logSufix);
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@Valid @PathVariable Long id) {
        logger.info("Fetching user with id: {} {}", id, logSufix);
        
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        logger.info("Adding user with id: {}, name: {}, email: {} {}", user.id, user.name, user.email, logSufix);
        userService.addUser(new User(user.id, user.name, user.email));
        return ResponseEntity.ok(user);
    }


    

}
