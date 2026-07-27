package com.example.DBdemo.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DBdemo.Model.User;
import com.example.DBdemo.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return userService.removeUser(user);
    }

    @PatchMapping("/{id}")
    public User patchUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return userService.partialUpdateUser(id, userDetails);
    }


}
