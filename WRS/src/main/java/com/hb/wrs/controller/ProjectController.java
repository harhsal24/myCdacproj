package com.hb.wrs.controller;

import com.hb.wrs.dto.ProjectDTO;
import com.hb.wrs.model.Project;
import com.hb.wrs.service.DTOConverterService;
import com.hb.wrs.service.ProjectService;
import com.hb.wrs.service.serviceimpl.ProjectServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @Autowired
    private DTOConverterService dtoConverterService;

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectServiceImpl.getProjectById(id);
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectServiceImpl.getAllProjects();
    }

    @GetMapping("/teamleader/{teamLeaderId}")
    public List<Project> getProjectsByTeamLeaderId(@PathVariable Long teamLeaderId) {
        return projectServiceImpl.getProjectsByTeamLeaderId(teamLeaderId);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Project>> getProjectsByEmployeeId(@PathVariable Long employeeId) {
        List<Project> projects = projectServiceImpl.getProjectsByEmployeeId(employeeId);
        return ResponseEntity.ok(projects);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody ProjectDTO projectDTO) {
        // Convert the ProjectDTO to Project entity and save
        Project project = dtoConverterService.convertToProjectEntity(projectDTO);
        Project createdProject = projectServiceImpl.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable Long projectId, @RequestBody Project project) {
        Project existingProject = projectServiceImpl.getProjectById(projectId);
        if (existingProject != null) {
            project.setProjectId(projectId);
            Project updatedProject = projectServiceImpl.updateProject(project);
            return ResponseEntity.ok(updatedProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        Project existingProject = projectServiceImpl.getProjectById(projectId);
        if (existingProject != null) {
            projectServiceImpl.deleteProject(projectId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


