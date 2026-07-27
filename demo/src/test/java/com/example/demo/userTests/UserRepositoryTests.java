package com.example.demo.userTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTests {

    @Spy
    private UserRepository userRepository;

    @Test
    public void shouldFetchAllUsers() {
        List<User> users = userRepository.getAllUsers();
        assertEquals(1, users.size());
        assertEquals("John", users.get(0).getName());
        verify(userRepository).getAllUsers();
        assertEquals(3L, users.get(0).getId());


        
    }

    @Test
    public void shouldFetchUserByIdWhenExists() {
        User user = userRepository.getUserById(3L).get();
        assertEquals("John", user.getName());
        verify(userRepository).getUserById(3L);
    }

    @Test
    public void shouldAddUserWhenValid() {
        User newUser = new User(2L, "Jane", "Jane@email.com");
        userRepository.addUser(newUser);
        List<User> users = Arrays.asList(new User(3L, "John", "john@email.com"), new User(2L, "Jane", "Jane@email.com"));
        assertEquals(2, userRepository.getAllUsers().size());
        assertEquals(users.get(0).getName(), userRepository.getAllUsers().get(0).getName());
        assertEquals(users.get(1).getName(), userRepository.getAllUsers().get(1).getName());
        verify(userRepository).addUser(newUser);

    }




}
