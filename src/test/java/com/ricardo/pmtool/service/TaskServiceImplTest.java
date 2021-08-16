package com.ricardo.pmtool.service;

import com.ricardo.pmtool.converter.TaskConverter;
import com.ricardo.pmtool.data.TaskData;
import com.ricardo.pmtool.persistence.model.Task;
import com.ricardo.pmtool.persistence.repository.TaskRepository;
import com.ricardo.pmtool.service.impl.TaskServiceImpl;
import com.ricardo.pmtool.status.TaskStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static com.ricardo.pmtool.testdata.TestData.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskConverter taskConverter;

    @Test //FIXME add more asserts
    public void testGetAllTasksSuccess() {
        List<Task> l = Arrays.asList(new Task(TEST_ID, TEST_DESCRIPTION, TEST_PROGRESS, TaskStatus.NEW, null, null, null), new Task(TEST_ID + 1, TEST_DESCRIPTION + 1, TEST_PROGRESS + 1, TaskStatus.NEW, null, null, null));
        when(taskRepository.findAll()).thenReturn(l);
        List<TaskData> projects = taskService.getAllTask();
        assertEquals(2, projects.size());
    }

}
