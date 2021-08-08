package com.ricardo.pmtool.converter;

import com.ricardo.pmtool.data.TaskData;
import com.ricardo.pmtool.persistence.model.Project;
import com.ricardo.pmtool.persistence.model.Task;
import com.ricardo.pmtool.persistence.model.User;
import com.ricardo.pmtool.persistence.repository.ProjectRepository;
import com.ricardo.pmtool.persistence.repository.UserRepository;
import com.ricardo.pmtool.status.TaskStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static com.ricardo.pmtool.testdata.TestData.*;
import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TaskConverter.class})
public class TaskConverterTest {

    @Autowired
    TaskConverter taskConverter;

    @MockBean
    UserRepository userRepository;

    @MockBean
    ProjectRepository projectRepository;

    private Task task;
    private Project project;
    private User user;

    @Before
    public void Before() {
        user = new User();
        user.setUsername(TEST_PM);
        project = new Project();
        project.setName(TEST_PM);
        project.setCode(TEST_CODE);
        project.setUser(user);
        task = new Task(TEST_ID, TEST_DESCRIPTION, TEST_PROGRESS, TaskStatus.NEW, null, project, user);
        task.setUser(user);
    }

    @Test
    public void testConvertTaskDTO() {
        when(userRepository.findByUsername(any())).thenReturn(ofNullable(user));
        when(projectRepository.findByCode(TEST_CODE)).thenReturn(ofNullable(project));
        Task expected = task;
        TaskData taskData = TEST_TASK1_DTO;
        taskData.setStatus("NEW");
        Task actual = taskConverter.convert(TEST_TASK1_DTO);
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getProgress(), actual.getProgress());
    }

    @Test
    public void testConvertTask() {
        when(userRepository.findByUsername(TEST_ASSIGNEE)).thenReturn(ofNullable(user));
        TaskData expected = TEST_TASK1_DTO;
        expected.setStatus("NEW");
        expected.setAssignee(TEST_PM);
        TaskData actual = taskConverter.convert(task);
        assertEquals(expected, actual);
    }


    @Test(expected = UsernameNotFoundException.class)
    public void testConvertTaskDTOError() {
        TaskData taskData = TEST_TASK1_DTO;
        taskData.setStatus("NEW");
        taskConverter.convert(TEST_TASK1_DTO);
    }

}
