package com.ricardo.pmtool.service.impl;

import com.ricardo.pmtool.data.UserData;
import com.ricardo.pmtool.persistence.model.Project;
import com.ricardo.pmtool.converter.ProjectConverter;
import com.ricardo.pmtool.data.ProjectData;
import com.ricardo.pmtool.persistence.model.User;
import com.ricardo.pmtool.persistence.repository.ProjectRepository;
import com.ricardo.pmtool.persistence.repository.UserRepository;
import com.ricardo.pmtool.roles.Role;
import com.ricardo.pmtool.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectConverter projectConverter;
    private final UserRepository userRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectConverter projectConverter, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.projectConverter = projectConverter;
        this.userRepository = userRepository;
    }

    @Override
    public List<ProjectData> getAllProjects() {
        return projectRepository.findAll().stream().map(projectConverter::convert).collect(Collectors.toList());
    }

    @Override
    public ProjectData getProjectById(Long id) {
        return null;
    }

    @Override
    public Long createProject(ProjectData projectData) {
        final Project project = projectConverter.convert(projectData);
        return projectRepository.save(project).getId();
    }

    @Override
    public void deleteProjectById(Long id) {
            projectRepository.deleteById(id);
    }

    @Override
    public void updateProject(ProjectData projectData, Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(()-> {
            throw new UsernameNotFoundException("Project with ID "+projectId+ " not found");
        });
        Optional<User> user = userRepository.findByUsername(projectData.getAssignee());
        if(user.isPresent()){
            project.setCode(projectData.getCode());
            project.setName(projectData.getName());
            project.setUser(user.get());
        }
        projectRepository.save(project);
    }
}
