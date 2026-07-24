package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

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

    public User getUserById(Long id) {
        for (User user : userlist) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        userlist.add(user);
    }

}
