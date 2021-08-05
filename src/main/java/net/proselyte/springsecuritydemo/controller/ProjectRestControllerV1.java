package net.proselyte.springsecuritydemo.controller;

import net.proselyte.springsecuritydemo.data.ProjectData;
import net.proselyte.springsecuritydemo.persistence.model.Project;
import net.proselyte.springsecuritydemo.persistence.repository.ProjectRepository;
import net.proselyte.springsecuritydemo.service.ProjectService;
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
