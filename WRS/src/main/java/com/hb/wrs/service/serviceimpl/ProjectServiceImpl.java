package com.hb.wrs.service.serviceimpl;

import com.hb.wrs.model.Project;
import com.hb.wrs.repository.ProjectRepository;
import com.hb.wrs.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> getProjectsByTeamLeaderId(Long teamLeaderId) {

        return projectRepository.findByTeamLeaderEmpId(teamLeaderId);
    }

    @Override
    public List<Project> getProjectsByEmployeeId(Long employeeId) {
        return projectRepository.findByEmployeesEmpId(employeeId);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }


    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }
    
}
