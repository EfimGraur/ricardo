package net.proselyte.springsecuritydemo.service;

import net.proselyte.springsecuritydemo.data.ProjectData;
import net.proselyte.springsecuritydemo.persistence.model.Project;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProjectService {
    List<ProjectData> getAllProjects();

    ProjectData getProjectById(Long id);

    ProjectData createProject(Project project);

    void deleteProjectById(Long id);
}
