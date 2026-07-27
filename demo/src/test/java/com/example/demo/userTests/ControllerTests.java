package com.example.demo.userTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private com.example.demo.service.UserService userService;

    @Test
    public void shouldAddUserWhenValid() throws Exception {
        when(userService.addUser(new com.example.demo.model.User(2L, "John Doe", "john.doe@email.com"))).thenReturn(new com.example.demo.model.User(2L, "John Doe", "john.doe@email.com"));
        mockMvc.perform(post("/users/add")
                .content("{\"id\":2,\"name\":\"John Doe\",\"email\":\"john.doe@email.com\"}")
                .contentType("application/json")).andExpect(status().isOk()).andExpect(content().json("{\"id\":2,\"name\":\"John Doe\",\"email\":\"john.doe@email.com\"}"));
    }

    @Test
    public void shouldFetchAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(java.util.Arrays.asList(new com.example.demo.model.User(3L, "John", "john@email.com"), new com.example.demo.model.User(2L, "John Doe", "john.doe@email.com")));
        mockMvc.perform(get("/users/all"))
                .andExpect(status().isOk()).andExpect(content().json("[{\"id\":3,\"name\":\"John\",\"email\":\"john@email.com\"}, {\"id\":2,\"name\":\"John Doe\",\"email\":\"john.doe@email.com\"}]"));
    }

    @Test
    public void shouldFetchUserByIdWhenExists() throws Exception {
        when(userService.getUserById(3L)).thenReturn(java.util.Optional.of(new com.example.demo.model.User(3L, "John", "john@email.com")));
        mockMvc.perform(get("/users/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.email").value("john@email.com"));

    }

    

    



}
