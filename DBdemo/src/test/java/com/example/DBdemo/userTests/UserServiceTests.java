package com.example.DBdemo.userTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import com.example.DBdemo.Exception.UserNotFoundException;
import com.example.DBdemo.Model.User;
import com.example.DBdemo.dto.UserRequest;
import com.example.DBdemo.dto.UserResponse;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
        UserRequest userRequest = new UserRequest("testuser", "Test", "User", "testuser@example.com", "password123");
        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("testuser");
        savedUser.setFirstname("Test");
        savedUser.setLastname("User");
        savedUser.setEmail("testuser@example.com");

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserResponse createdUser = userService.createUser(userRequest);

        verify(userRepository).save(any(User.class));
        assertEquals(1L, createdUser.getId());
        assertEquals("testuser", createdUser.getUsername());
    }

    @Test
    public void shouldRemoveUserIfValid() {
        User user = new User();
        user.setId(1L);
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.removeUser(user);

        verify(userRepository).delete(user);
    }

    @Test
    public void shouldGetUserByIdIfValid() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));

        userService.getUserById(userId);

        verify(userRepository).findById(userId);
    }

    @Test
    public void shouldGetUserByUsernameIfValid() {
        String username = "testuser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(new User()));

        userService.getUserByUsername(username);

        verify(userRepository).findByUsername(username);
    }

    @Test
    public void shouldGetUserByEmailIfValid() {
        String email = "testuser@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User()));

        userService.getUserByEmail(email);

        verify(userRepository).findByEmail(email);
    }

    @Test
    public void shouldFindAllUsers() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        userService.findAllUsers();

        verify(userRepository).findAll();
    }

    @Test
    public void shouldReturnEmptyListWhenNoUsersFound() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        List<UserResponse> users = userService.findAllUsers();

        assertEquals(0, users.size());
        verify(userRepository).findAll();
    }

    @Test
    public void shouldSearchUsersByUsernameIfValid() {
        String username = "test";
        when(userRepository.findTop10ByUsernameContainingIgnoreCaseOrderByUsernameAsc(username)).thenReturn(Collections.emptyList());

        userService.searchUsersByUsername(username);

        verify(userRepository).findTop10ByUsernameContainingIgnoreCaseOrderByUsernameAsc(username);
    }

    @Test
    public void shouldCountUsers() {
        when(userRepository.countUsers()).thenReturn(5);
        userService.countUsers();
        verify(userRepository).countUsers();
    }

    @Test
    public void shouldThrowWhenUserNotFoundById() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
        verify(userRepository).findById(userId);
    }
}
