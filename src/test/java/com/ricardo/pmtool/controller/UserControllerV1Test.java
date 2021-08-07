package com.ricardo.pmtool.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricardo.pmtool.persistence.model.User;
import com.ricardo.pmtool.service.UserService;
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

import static com.ricardo.pmtool.constants.GenericConstants.ALL_USERS_QUERY_PARAM;
import static com.ricardo.pmtool.constants.RequestMappings.USERS_URL;
import static com.ricardo.pmtool.constants.RequestMappings.USERS_URL_BY_ID;
import static com.ricardo.pmtool.testdata.TestData.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserControllerV1.class)
@ActiveProfiles({"controller_unit_test"})
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetAllUsersSuccess() throws Exception {
        when(userService
                .getAllUsers(ALL_USERS_QUERY_PARAM))
                .thenReturn(Arrays.asList(TEST_USER1, TEST_USER2));
        String expectedResult =
                "[{id:1,email:dummy_email, firstName:dummy_first_name, lastName: dummy_last_name, username: dummy_username, role:ADMIN, password:null}," +
                        "{id:2,email:dummy_email2, firstName:dummy_first_name2, lastName: dummy_last_name2, username: dummy_username2, role:ADMIN, password:null}]";


        mockMvc
                .perform(get(USERS_URL))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult))
                .andReturn();
    }


    @Test(expected = org.springframework.web.util.NestedServletException.class)
    public void testGetAllUsersFail() throws Exception {
        when(userService
                .getAllUsers(ALL_USERS_QUERY_PARAM))
                .thenThrow(new RuntimeException());

        mockMvc
                .perform(get(USERS_URL))
                .andExpect(status().is5xxServerError())
                .andReturn();
    }

    @Test
    public void testGetUserByIdSuccess() throws Exception {
        String expectedResult =
                "{id:1,email:dummy_email, firstName:dummy_first_name, lastName: dummy_last_name, username: dummy_username, role:ADMIN, password:null}";

        when(userService
                .getUserById(TEST_ID))
                .thenReturn(TEST_USER1);

        mockMvc
                .perform(get(USERS_URL_BY_ID, TEST_ID))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult))
                .andReturn();
    }

    @Test
    public void testCreateUserSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(USERS_URL)
                .content(asJsonString(new User()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateUserSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put(USERS_URL_BY_ID, TEST_ID)
                .content(asJsonString(new User()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteUserSuccess() throws Exception {
        mockMvc
                .perform(delete(USERS_URL_BY_ID, TEST_ID))
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
