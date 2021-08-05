package com.ricardo.pmtool.service.impl;

import com.ricardo.pmtool.persistence.model.Project;
import com.ricardo.pmtool.converter.ProjectConverter;
import com.ricardo.pmtool.data.ProjectData;
import com.ricardo.pmtool.persistence.repository.ProjectRepository;
import com.ricardo.pmtool.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectConverter projectConverter;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectConverter projectConverter) {
        this.projectRepository = projectRepository;
        this.projectConverter = projectConverter;
    }

    @Override
    public List<ProjectData> getAllProjects() {
        return projectRepository.findAll().stream().map(project -> projectConverter.convert(project)).collect(Collectors.toList());
    }

    @Override
    public ProjectData getProjectById(Long id) {
        return null;
    }

    @Override
    public ProjectData createProject(Project project) {
        return null;
    }

    @Override
    public void deleteProjectById(Long id) {

    }
}
