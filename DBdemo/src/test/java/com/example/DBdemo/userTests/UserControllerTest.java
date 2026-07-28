package com.example.DBdemo.userTests;

import com.example.DBdemo.Controller.UserController;
import com.example.DBdemo.Service.UserService;
import com.example.DBdemo.dto.UserPatchRequest;
import com.example.DBdemo.dto.UserRequest;
import com.example.DBdemo.dto.UserResponse;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        userResponse = new UserResponse(
                1L,
                "testuser",
                "Test",
                "User",
                "testuser@example.com",
                LocalDateTime.of(2026, 1, 1, 12, 0)
        );
    }

    @Test
    void testGetAllUsers() throws Exception {
        when(userService.findAllUsers()).thenReturn(List.of(userResponse));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].username").value("testuser"))
                .andExpect(jsonPath("$[0].email").value("testuser@example.com"));

        verify(userService, times(1)).findAllUsers();
    }

    @Test
    void testCreateUserWithValidData() throws Exception {
        String userJson = """
                {
                  "username":"testuser",
                  "firstname":"Test",
                  "lastname":"User",
                  "email":"testuser@example.com",
                  "password":"password123"
                }
                """;

        when(userService.createUser(any(UserRequest.class))).thenReturn(userResponse);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("testuser@example.com"));

        verify(userService, times(1)).createUser(any(UserRequest.class));
    }

    @Test
    void testCreateUserWithInvalidData() throws Exception {
        String invalidUserJson = """
                {
                  "username":"",
                  "firstname":"Test",
                  "lastname":"User",
                  "email":"invalid-email",
                  "password":"short"
                }
                """;

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidUserJson))
                .andExpect(status().isBadRequest());

        verify(userService, never()).createUser(any(UserRequest.class));
    }

    @Test
    void testUpdateUserWithValidData() throws Exception {
        String userJson = """
                {
                  "username":"updateduser",
                  "firstname":"Updated",
                  "lastname":"User",
                  "email":"updated@example.com",
                  "password":"newpassword123"
                }
                """;

        when(userService.updateUser(anyLong(), any(UserRequest.class))).thenReturn(userResponse);

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(userService, times(1)).updateUser(anyLong(), any(UserRequest.class));
    }

    @Test
    void testUpdateUserWithInvalidData() throws Exception {
        String invalidUserJson = """
                {
                  "username":"",
                  "firstname":"Updated",
                  "lastname":"User",
                  "email":"not-an-email",
                  "password":"123"
                }
                """;

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidUserJson))
                .andExpect(status().isBadRequest());

        verify(userService, never()).updateUser(anyLong(), any(UserRequest.class));
    }

    @Test
    void testUpdateUserNotFound() throws Exception {
        String userJson = """
                {
                  "username":"updateduser",
                  "firstname":"Updated",
                  "lastname":"User",
                  "email":"updated@example.com",
                  "password":"newpassword123"
                }
                """;

        when(userService.updateUser(anyLong(), any(UserRequest.class)))
                .thenThrow(new RuntimeException("User not found"));

        assertThrows(ServletException.class, () ->
                mockMvc.perform(put("/api/users/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
        );
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).removeUserById(1L);
    }

    @Test
    void testDeleteUserNotFound() throws Exception {
        when(userService.removeUserById(anyLong()))
                .thenThrow(new RuntimeException("User not found"));

        assertThrows(ServletException.class, () -> mockMvc.perform(delete("/api/users/999")));
    }

    @Test
    void testPatchUserWithValidData() throws Exception {
        String patchJson = """
                {
                  "email":"newemail@example.com"
                }
                """;

        when(userService.partialUpdateUser(anyLong(), any(UserPatchRequest.class))).thenReturn(userResponse);

        mockMvc.perform(patch("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patchJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("testuser@example.com"));

        verify(userService, times(1)).partialUpdateUser(anyLong(), any(UserPatchRequest.class));
    }

    @Test
    void testPatchUserWithInvalidData() throws Exception {
        String invalidPatchJson = """
                {
                  "email":"invalid email"
                }
                """;

        mockMvc.perform(patch("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPatchJson))
                .andExpect(status().isBadRequest());

        verify(userService, never()).partialUpdateUser(anyLong(), any(UserPatchRequest.class));
    }
}
