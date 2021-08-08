package com.ricardo.pmtool.service;

import com.ricardo.pmtool.converter.ProjectConverter;
import com.ricardo.pmtool.converter.UserConverter;
import com.ricardo.pmtool.data.ProjectData;
import com.ricardo.pmtool.data.UserData;
import com.ricardo.pmtool.persistence.model.Project;
import com.ricardo.pmtool.persistence.model.User;
import com.ricardo.pmtool.persistence.repository.ProjectRepository;
import com.ricardo.pmtool.persistence.repository.UserRepository;
import com.ricardo.pmtool.roles.Role;
import com.ricardo.pmtool.service.impl.ProjectServiceImpl;
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
public class ProjectServiceImplTest {

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock ProjectConverter projectConverter;

    @Test //FIXME add more asserts
    public void testGetAllProjectsSuccess() {
        List<Project> l = Arrays.asList(new Project(TEST_ID,TEST_CODE, TEST_PROJECT_NAME, null, null),new Project(TEST_ID+1,TEST_CODE+1, TEST_PROJECT_NAME+1, null, null));
        when(projectRepository.findAll()).thenReturn(l);
        List<ProjectData> projects = projectService.getAllProjects();
        assertEquals(2, projects.size());
    }

}
