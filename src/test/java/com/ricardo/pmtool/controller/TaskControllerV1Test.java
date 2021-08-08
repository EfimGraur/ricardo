package com.ricardo.pmtool.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricardo.pmtool.persistence.model.Task;
import com.ricardo.pmtool.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static com.ricardo.pmtool.constants.RequestMappings.TASKS_URL;
import static com.ricardo.pmtool.constants.RequestMappings.TASKS_URL_BY_ID;
import static com.ricardo.pmtool.testdata.TestData.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TaskControllerV1.class)
@ActiveProfiles({"controller_unit_test"})
@AutoConfigureMockMvc(addFilters = false)
public class TaskControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    public void testGetAllTasksSuccess() throws Exception {
        when(taskService
                .getAllTask())
                .thenReturn(Arrays.asList(TEST_TASK1_DTO, TEST_TASK2_DTO));
        String expectedResult =
                "[{id:1,description:dummy_description, pm:dummy_pm, progress:12, status:dummy_status, projectCode:dummy_code, assignee:dummy_assignee}," +
                        "{id:2,description:dummy_description2, pm:dummy_pm, progress:12, status:dummy_status, projectCode:dummy_code2, assignee:dummy_assignee2}]";

        mockMvc
                .perform(get(TASKS_URL))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult))
                .andReturn();
    }


    @Test(expected = org.springframework.web.util.NestedServletException.class)
    public void testGetAllTasksFail() throws Exception {
        when(taskService
                .getAllTask())
                .thenThrow(new RuntimeException());

        mockMvc
                .perform(get(TASKS_URL))
                .andExpect(status().is5xxServerError())
                .andReturn();
    }

    @Test
    public void testGetTaskByIdSuccess() throws Exception {
        String expectedResult =
                "{id:1,description:dummy_description, pm:dummy_pm, progress:12, status:dummy_status, projectCode:dummy_code, assignee:dummy_assignee}";

        when(taskService
                .getTaskById(TEST_ID))
                .thenReturn(TEST_TASK1_DTO);

        mockMvc
                .perform(get("/api/v1/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult))
                .andReturn();
    }

    @Test
    public void testCreateTaskSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(TASKS_URL)
                .content(asJsonString(new Task()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateTaskSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put(TASKS_URL_BY_ID, TEST_ID)
                .content(asJsonString(new Task()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteTaskSuccess() throws Exception {
        mockMvc
                .perform(delete(TASKS_URL_BY_ID, TEST_ID))
                .andExpect(status().isOk())
                .andReturn();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
