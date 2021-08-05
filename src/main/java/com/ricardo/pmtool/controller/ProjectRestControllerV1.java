package com.ricardo.pmtool.controller;

import com.ricardo.pmtool.persistence.model.Project;
import com.ricardo.pmtool.data.ProjectData;
import com.ricardo.pmtool.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectRestControllerV1 {

    private final ProjectService projectService;

    @Autowired
    public ProjectRestControllerV1(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('projects:read')")
    public List<ProjectData> getAll() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('projects:read')")
    public ProjectData getById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('projects:write')")
    public ProjectData create(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('projects:write')")
    public void deleteById(@PathVariable Long id) {
        projectService.deleteProjectById(id);
    }
}
