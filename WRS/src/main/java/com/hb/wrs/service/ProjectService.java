package com.hb.wrs.service;

import com.hb.wrs.model.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();

    Project getProjectById(Long projectId);

    Project createProject(Project project);

    Project updateProject(Long projectId, Project project);

    void deleteProject(Long projectId);
}
