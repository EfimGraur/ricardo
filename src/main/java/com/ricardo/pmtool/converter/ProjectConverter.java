package com.ricardo.pmtool.converter;

import com.ricardo.pmtool.persistence.model.Project;
import com.ricardo.pmtool.data.ProjectData;
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
