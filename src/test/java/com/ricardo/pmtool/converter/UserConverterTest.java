package com.ricardo.pmtool.converter;

import com.ricardo.pmtool.data.UserData;
import com.ricardo.pmtool.persistence.model.User;
import com.ricardo.pmtool.roles.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static com.ricardo.pmtool.testdata.TestData.*;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserConverter.class})
public class UserConverterTest {

    @Autowired
    UserConverter userConverter;

    @MockBean
    PasswordEncoder passwordEncoder;

    private User user;

    @Before
    public void Before() {
        user = new User(TEST_ID, TEST_EMAIL, "", TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USERNAME, Role.ADMIN, null, null);
    }

    @Test
    public void testConvertUser() {
        UserData expected = TEST_USER1_DTO;
        UserData actual = userConverter.convert(user);
        assertEquals(expected, actual);
    }

    @Test
    public void testConvertUseDTO() {
        User expected = user;
        User actual = userConverter.convert(TEST_USER1_DTO);
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getRole(), actual.getRole());
    }

}
