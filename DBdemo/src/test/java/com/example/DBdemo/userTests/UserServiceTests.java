package com.example.DBdemo.userTests;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import com.example.DBdemo.Model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.DBdemo.Repository.UserRepository;
import com.example.DBdemo.Service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldCreateUserIfValid() {
        when(userRepository.save(any(User.class))).thenReturn(new User());
        userService.createUser(new User());
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void shouldRemoveUserIfValid() {
        // No need to stub delete method as it returns void
        User user = new User();
        userService.removeUser(user);
        verify(userRepository).delete(user);
    }

    @Test
    public void shouldGetUserByIdIfValid() {
        long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(new User());
        userService.getUserById(userId);
        verify(userRepository).findById(userId);
    }

    @Test
    public void shouldGetUserByUsernameIfValid() {  
        String username = "testuser";
        when(userRepository.findByUsername(username)).thenReturn(new User());
        userService.getUserByUsername(username);
        verify(userRepository).findByUsername(username);
    }

    @Test
    public void shouldGetUserByEmailIfValid() {
        String email = "testuser@example.com";
        when(userRepository.findByEmail(email)).thenReturn(new User());
        userService.getUserByEmail(email);
        verify(userRepository).findByEmail(email);
    }

    @Test
    public void shouldFindAllUsers() {
        when(userRepository.findAll()).thenReturn(java.util.Collections.emptyList());
        userService.findAllUsers();
        verify(userRepository).findAll();
    }

    @Test
    public void shouldSearchUsersByUsernameIfValid() {
        String username = "test";
        when(userRepository.findTop10ByUsernameContainingIgnoreCaseOrderByUsernameAsc(username)).thenReturn(java.util.Collections.emptyList());
        userService.searchUsersByUsername(username);
        verify(userRepository).findTop10ByUsernameContainingIgnoreCaseOrderByUsernameAsc(username);
    }

    @Test
    public void shouldCountUsers() {
        when(userRepository.countUsers()).thenReturn(5);
        userService.countUsers();
        verify(userRepository).countUsers();
    }


    
}
