package net.proselyte.springsecuritydemo.converter;

import net.proselyte.springsecuritydemo.data.ProjectData;
import net.proselyte.springsecuritydemo.persistence.model.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter {
    public ProjectData convert(final Project project) {
        final ProjectData projectData = new ProjectData();
        projectData.setId(project.getId());
        projectData.setCode(project.getCode());
        projectData.setName(project.getName());
        projectData.setAssignee(project.getUser().getUsername());
        return projectData;
    }
}
