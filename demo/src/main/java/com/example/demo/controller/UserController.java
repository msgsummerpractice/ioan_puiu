package com.example.demo.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class); 

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping("/all")
    public Object getAllUsers() {
        logger.info("Fetching all users");
        return userService.getAllUsers();
    }

    @RequestMapping("/{id}")
    public Object getUserById(@PathVariable Long id) {
        logger.info("Fetching user with id: {}", id);
        return userService.getUserById(id);
    }

    @RequestMapping("/add")
    public Object addUser(@RequestParam Long id, @RequestParam String name, @RequestParam String email) {
        logger.info("Adding user with id: {}, name: {}, email: {}", id, name, email);
        userService.addUser(new User(id, name, email));
        return "User added successfully";
    }


    

}
