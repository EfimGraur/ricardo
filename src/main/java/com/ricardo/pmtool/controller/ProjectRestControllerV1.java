package com.ricardo.pmtool.controller;

import com.ricardo.pmtool.data.UserData;
import com.ricardo.pmtool.persistence.model.Project;
import com.ricardo.pmtool.data.ProjectData;
import com.ricardo.pmtool.service.ProjectService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Object> createProject(@RequestBody ProjectData projectData) {
        try
        {
            Long newProjectId = projectService.createProject(projectData);

            URI newUserLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/api/v1/projects/{id}")
                    .buildAndExpand(newProjectId)
                    .toUri();

            return ResponseEntity.created(newUserLocation).build();
        }
        catch (final Exception e)
        {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('projects:write')")
    public void deleteProjectById(@PathVariable Long id) {
        projectService.deleteProjectById(id);
    }
}
