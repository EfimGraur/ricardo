package com.ricardo.pmtool.service;

import com.ricardo.pmtool.data.TaskData;
import com.ricardo.pmtool.data.ProjectData;
import com.ricardo.pmtool.data.UserData;

import java.util.List;

public interface UserService {
    ProjectData getProject(long userId, long projectId);

    List<ProjectData> getAllProjectsByUser(long userId);

    List<TaskData> getAllTasksByPM(long userId);

    List<TaskData> getAllTasksByUser(long userId);

    List<UserData> getAllUsers(String role);

    UserData getUserById(long userId);

    void deleteUserById(long userId);

    Long createUser(UserData userData);

    void updateUser(UserData userData, Long userId);
}
