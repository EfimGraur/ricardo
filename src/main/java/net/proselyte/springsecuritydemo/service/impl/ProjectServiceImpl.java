package net.proselyte.springsecuritydemo.service.impl;

import net.proselyte.springsecuritydemo.converter.ProjectConverter;
import net.proselyte.springsecuritydemo.data.ProjectData;
import net.proselyte.springsecuritydemo.persistence.model.Project;
import net.proselyte.springsecuritydemo.persistence.repository.ProjectRepository;
import net.proselyte.springsecuritydemo.service.ProjectService;
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
