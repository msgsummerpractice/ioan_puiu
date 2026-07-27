package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.demo.model.User;


@Repository
public class UserRepository {
    private List<User> userlist = new ArrayList<User>();

    public UserRepository() {
        userlist.add(new User(3L,"John","john@email.com"));
    }

    public UserRepository(List<User> userlist) {
        this.userlist = userlist;
    }

    public List<User> getAllUsers() {
        return userlist;
    }

    public Optional<User> getUserById(Long id) {
        return userlist.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public User addUser(User user) {
        userlist.add(user);
        return user;
    }



}
