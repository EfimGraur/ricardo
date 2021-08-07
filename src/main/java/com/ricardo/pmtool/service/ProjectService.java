package com.ricardo.pmtool.service;

import com.ricardo.pmtool.data.UserData;
import com.ricardo.pmtool.persistence.model.Project;
import com.ricardo.pmtool.data.ProjectData;

import java.util.List;

public interface ProjectService {
    List<ProjectData> getAllProjects();

    ProjectData getProjectById(Long id);

    Long createProject(ProjectData projectData);

    void deleteProjectById(Long id);

    void updateProject(ProjectData projectData, Long projectId);
}
