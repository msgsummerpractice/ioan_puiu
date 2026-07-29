package com.example.demo.userTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldFetchAllUsers() {
        List<User> users = Arrays.asList(new User(1L, "John", "john@email.com"),
         new User(2L, "Jane", "jane@email.com"));
        when(userRepository.getAllUsers()).thenReturn(users);


        List<User> result = userService.getAllUsers();
        assertEquals(users, result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getName());
        assertEquals("Jane", result.get(1).getName());
        verify(userRepository).getAllUsers();
    }

    @Test
    public void shouldFetchUserByIdWhenExists() {
        Optional<User> user = Optional.of(new User(1L, "John", "John@email.com"));
        when(userRepository.getUserById(1L)).thenReturn(user);


        Optional<User> result = userService.getUserById(1L);
        assertEquals(user, result);
        assertEquals("John", result.get().getName());
        verify(userRepository).getUserById(1L);

    }
    
    @Test
    public void shouldAddUserWhenValid() {
        User newUser = new User(2L, "Jane", "Jane@email.com");
        when(userRepository.addUser(newUser)).thenReturn(newUser);
        User result = userService.addUser(newUser);
        assertEquals(newUser, result);
        verify(userRepository).addUser(newUser);
    }
    
}
