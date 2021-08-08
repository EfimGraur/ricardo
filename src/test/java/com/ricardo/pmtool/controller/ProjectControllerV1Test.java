package com.ricardo.pmtool.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricardo.pmtool.persistence.model.Project;
import com.ricardo.pmtool.service.ProjectService;
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

import static com.ricardo.pmtool.constants.RequestMappings.PROJECTS_URL;
import static com.ricardo.pmtool.constants.RequestMappings.PROJECTS_URL_BY_ID;
import static com.ricardo.pmtool.testdata.TestData.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProjectControllerV1.class)
@ActiveProfiles({"controller_unit_test"})
@AutoConfigureMockMvc(addFilters = false)
public class ProjectControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @Test
    public void testGetAllProjectsSuccess() throws Exception {
        when(projectService
                .getAllProjects())
                .thenReturn(Arrays.asList(TEST_PROJECT1_DTO, TEST_PROJECT2_DTO));
        String expectedResult =
                "[{id:1,code:dummy_code, name:dummy_project_name, assignee: dummy_assignee}," +
                        "{id:2,code:dummy_code2, name:dummy_project_name2, assignee: dummy_assignee2}]";

        mockMvc
                .perform(get(PROJECTS_URL))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult))
                .andReturn();
    }


    @Test(expected = org.springframework.web.util.NestedServletException.class)
    public void testGetAllProjectsFail() throws Exception {
        when(projectService
                .getAllProjects())
                .thenThrow(new RuntimeException());

        mockMvc
                .perform(get(PROJECTS_URL))
                .andExpect(status().is5xxServerError())
                .andReturn();
    }

    @Test
    public void testGetProjectByIdSuccess() throws Exception {
        String expectedResult =
                "{id:1,code:dummy_code, name:dummy_project_name, assignee: dummy_assignee}";

        when(projectService
                .getProjectById(TEST_ID))
                .thenReturn(TEST_PROJECT1_DTO);

        mockMvc
                .perform(get(PROJECTS_URL_BY_ID, TEST_ID))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult))
                .andReturn();
    }

    @Test
    public void testCreateProjectSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(PROJECTS_URL)
                .content(asJsonString(new Project()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateProjectSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put(PROJECTS_URL_BY_ID, TEST_ID)
                .content(asJsonString(new Project()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteProjectSuccess() throws Exception {
        mockMvc
                .perform(delete(PROJECTS_URL_BY_ID, TEST_ID))
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
