package com.ricardo.pmtool.IT;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.ricardo.pmtool.constants.RequestMappings.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UnauthorizedUser_IT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("When calling the /api/v1/users without authentication we expect to get 403")
    public void testUnauthenticatedGetAllUsers() throws Exception {
        mockMvc.perform(get(USERS_URL))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("When calling the /api/v1/projects without authentication we expect to get 403")
    public void testUnauthenticatedGetAllProjects() throws Exception {
        mockMvc.perform(get(PROJECTS_URL))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("When calling the /api/v1/tasks without authentication we expect to get 403")
    public void testUnauthenticatedGetAllTasks() throws Exception {
        mockMvc.perform(get(TASKS_URL))
                .andExpect(status().isForbidden());
    }

}
