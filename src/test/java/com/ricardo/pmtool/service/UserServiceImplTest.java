package com.ricardo.pmtool.service;

import com.ricardo.pmtool.converter.UserConverter;
import com.ricardo.pmtool.data.UserData;
import com.ricardo.pmtool.persistence.model.User;
import com.ricardo.pmtool.persistence.repository.UserRepository;
import com.ricardo.pmtool.roles.Role;
import com.ricardo.pmtool.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static com.ricardo.pmtool.constants.GenericConstants.ALL_USERS_QUERY_PARAM;
import static com.ricardo.pmtool.testdata.TestData.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserConverter userConverter;

    @Mock
    private UserRepository userRepository;

    private User testUser1;

    @Before
    public void Before() {
        testUser1 = new User(TEST_ID, TEST_EMAIL, "", TEST_FIRST_NAME, TEST_LAST_NAME, TEST_ADMIN_ROLE, Role.ADMIN, null, null);
    }

    @Test //FIXME
    public void testGetAllUsersSuccess() {

        User testUser2 = new User(TEST_ID + 1, TEST_EMAIL + 2, "", TEST_FIRST_NAME + 2, TEST_LAST_NAME + 2, TEST_ADMIN_ROLE, Role.ADMIN, null, null);
        List<User> l = Arrays.asList(testUser1, testUser2);
        when(userRepository.findAll()).thenReturn(l);
        List<UserData> users = userService.getAllUsers(ALL_USERS_QUERY_PARAM);
        assertEquals(2, users.size());
    }

    //FIXME
    @Test
    @Ignore
    public void testGetUserByIdSuccess() {
        when(userRepository.getById(TEST_ID)).thenReturn(testUser1);
        UserData user = userService.getUserById(TEST_ID);
        System.out.println(user);
        assertEquals(2, 2);
    }
}
