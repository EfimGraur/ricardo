package com.ricardo.pmtool.converter;

import com.ricardo.pmtool.data.ProjectData;
import com.ricardo.pmtool.persistence.model.Project;
import com.ricardo.pmtool.persistence.model.User;
import com.ricardo.pmtool.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter {
    private final UserRepository userRepository;

    @Autowired
    public ProjectConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ProjectData convert(final Project project) {
        ProjectData projectData = new ProjectData();
        projectData.setId(project.getId());
        projectData.setCode(project.getCode());
        projectData.setName(project.getName());
        if (project.getUser() != null) {
            projectData.setAssignee(project.getUser().getUsername());
        }
        return projectData;
    }

    public Project convert(final ProjectData projectData) {
        Project project = new Project();
        project.setId(projectData.getId());
        project.setCode(projectData.getCode());
        project.setName(projectData.getName());
        if (projectData.getAssignee() != null) {
            User user = userRepository.findByUsername(projectData.getAssignee()).orElseThrow(() -> {
                throw new UsernameNotFoundException("Username " + projectData.getAssignee() + " not found");
            });
            project.setUser(user);
        }
        return project;
    }
}
