package com.ricardo.pmtool.converter;

import com.ricardo.pmtool.data.ProjectData;
import com.ricardo.pmtool.data.UserData;
import com.ricardo.pmtool.persistence.model.Project;
import com.ricardo.pmtool.persistence.model.User;
import com.ricardo.pmtool.persistence.repository.UserRepository;
import com.ricardo.pmtool.roles.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


import static com.ricardo.pmtool.testdata.TestData.*;
import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProjectConverter.class})
public class ProjectConverterTest {

    @Autowired
    ProjectConverter projectConverter;

    @MockBean
    UserRepository userRepository;

     Project project;
     User user;

    @Before
    public void Before() {
        user = new User();
        user.setUsername(TEST_ASSIGNEE);
        project = new Project(TEST_ID,TEST_CODE, TEST_PROJECT_NAME, null, user);
    }

    @Test
    public void testConvertProject() {
        ProjectData expected = TEST_PROJECT1_DTO;
        ProjectData actual = projectConverter.convert(project);
        assertEquals(expected, actual);
    }

    @Test
    public void testConvertUseDTO() {
        when(userRepository.findByUsername(TEST_ASSIGNEE)).thenReturn(ofNullable(user));
        Project expected = project;
        Project actual = projectConverter.convert(TEST_PROJECT1_DTO);
        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testConvertUseDTOError() {
       projectConverter.convert(TEST_PROJECT1_DTO);
    }

}
