package com.ricardo.pmtool.controller;

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

import static com.ricardo.pmtool.constants.RequestMappings.PROJECTS_URL;

@RestController
@RequestMapping(PROJECTS_URL)
public class ProjectControllerV1 {

    private final ProjectService projectService;

    @Autowired
    public ProjectControllerV1(ProjectService projectService) {
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
        try {
            Long newProjectId = projectService.createProject(projectData);

            URI newUserLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/api/v1/projects/{id}")
                    .buildAndExpand(newProjectId)
                    .toUri();

            return ResponseEntity.created(newUserLocation).build();
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.SC_CONFLICT).build();
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{projectId}")
    @PreAuthorize("hasAuthority('projects:write')")
    public ResponseEntity<Object> updateProject(@PathVariable Long projectId, @RequestBody ProjectData projectData) {
        try {
            projectService.updateProject(projectData, projectId);

            return ResponseEntity.noContent().build();
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.SC_CONFLICT).build();
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('projects:write')")
    public void deleteProjectById(@PathVariable Long id) {
        projectService.deleteProjectById(id);
    }
}
