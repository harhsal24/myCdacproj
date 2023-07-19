package com.hb.wrs.service;

import java.util.List;

import com.hb.wrs.model.Project;

public interface ProjectService {
    Project getProjectById(Long projectId);

    List<Project> getAllProjects();

    List<Project> getProjectsByTeamLeaderId(Long teamLeaderId);

    List<Project> getProjectsByEmployeeId(Long employeeId);

    Project createProject(Project project);

    void deleteProject(Long projectId);

    Project updateProject(Project project);
}
