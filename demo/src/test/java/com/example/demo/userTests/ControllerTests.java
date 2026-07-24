package com.example.demo.userTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAddUser() throws Exception {
        mockMvc.perform(get("/users/add")
                .param("id", "2")
                .param("name", "John Doe")
                .param("email", "john.doe@email.com")).andExpect(status().isOk()).andExpect(content().string("User added successfully"));
    }

    @Test
    void testAllUser() throws Exception {
        mockMvc.perform(get("/users/all"))
                .andExpect(status().isOk()).andExpect(content().json("[{\"id\":3,\"name\":\"John\",\"email\":\"john@email.com\"}, {\"id\":2,\"name\":\"John Doe\",\"email\":\"john.doe@email.com\"}]"));
    }

    @Test
    void testGetUserById() throws Exception {
        mockMvc.perform(get("/users/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.email").value("john@email.com"));

    }

    



}
