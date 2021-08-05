package net.proselyte.springsecuritydemo.service;

import net.proselyte.springsecuritydemo.data.ProjectData;
import net.proselyte.springsecuritydemo.data.TaskData;
import net.proselyte.springsecuritydemo.data.UserData;

import java.util.List;

public interface UserService {
    ProjectData getProject(long userId, long projectId);

    List<ProjectData> getAllProjectsByUser(long userId);

    List<TaskData> getAllTasksByPM(long userId);

    List<TaskData> getAllTasksByUser(long userId);

    List<UserData> getUsers();
}
