package com.hb.wrs.service;

import com.hb.wrs.dto.EmployeeDTO;
import com.hb.wrs.dto.ProjectDTO;
import com.hb.wrs.dto.WeeklyReportDTO;
import com.hb.wrs.model.Employee;
import com.hb.wrs.model.Project;
import com.hb.wrs.model.WeeklyReport;
import com.hb.wrs.repository.EmployeeRepository;
import com.hb.wrs.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DTOConverterService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Project convertToProjectEntity(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setProjectName(projectDTO.getProjectName());
        project.setProjectType(projectDTO.getProjectType());

        if (projectDTO.getTeamLeader() != null) {
            EmployeeDTO teamLeaderDTO = projectDTO.getTeamLeader();
            Employee teamLeader = employeeRepository.findById(teamLeaderDTO.getEmpId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid team leader ID"));
            project.setTeamLeader(teamLeader);
        }

        if (projectDTO.getEmployees() != null) {
            List<Employee> employees = new ArrayList<>();
            for (EmployeeDTO employeeDTO : projectDTO.getEmployees()) {
                Employee employee = employeeRepository.findById(employeeDTO.getEmpId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));
                employees.add(employee);
            }
            project.setEmployees(employees);
        }

        return project;
    }

    public WeeklyReport convertToWeeklyReportEntity(
            WeeklyReportDTO reportDTO, EmployeeDTO employeeDTO, ProjectDTO projectDTO) {
        Employee employee = new Employee();
        employee.setEmpId(employeeDTO.getEmpId());

        Project project = convertToProjectEntity(projectDTO);

        WeeklyReport report = new WeeklyReport();
        report.setEmployee(employee);
        report.setProject(project);
        report.setPlannedCompletionDate(reportDTO.getPlannedCompletionDate());
        report.setActualCompletionDate(reportDTO.getActualCompletionDate());
        report.setDeliverables(reportDTO.getDeliverables());
        return report;
    }

}
