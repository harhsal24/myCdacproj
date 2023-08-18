package com.hb.wrs.service;

import com.hb.wrs.dto.EmployeeDTO;
import com.hb.wrs.dto.ProjectDTO;
import com.hb.wrs.dto.WeeklyReportDTO;
import com.hb.wrs.model.Employee;
import com.hb.wrs.model.Project;
import com.hb.wrs.model.WeeklyReport;
import com.hb.wrs.repository.EmployeeRepository;
import com.hb.wrs.repository.ProjectRepository;
import com.hb.wrs.repository.WeeklyReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DTOConverterService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private WeeklyReportRepository weeklyReportRepository;


    public EmployeeDTO convertToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        // Convert fields and return DTO
        return employeeDTO;
    }


    public ProjectDTO convertToProjectDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId(project.getProjectId());
        projectDTO.setProjectName(project.getProjectName());
        // Set other fields...
        return projectDTO;
    }

    public WeeklyReportDTO convertToWeeklyReportDTO(WeeklyReport report) {
        WeeklyReportDTO reportDTO = new WeeklyReportDTO();
        reportDTO.setReportId(report.getReportId());
        // Set other fields...
        return reportDTO;
    }

    public Project convertToProjectEntity(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setProjectName(projectDTO.getProjectName());
//        project.setProjectType(projectDTO.getProjectType());
//        project.setStartDate(projectDTO.getStartDate());
//        project.setExpectedEndDate(projectDTO.getExpectedEndDate());

        if (projectDTO.getTeamLeaderId() != null) {
            Employee teamLeader = employeeRepository.findById(projectDTO.getTeamLeaderId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid team leader ID"));
            project.setTeamLeader(teamLeader);
        }

        if (projectDTO.getEmployeeIds() != null) {
            List<Employee> employees = new ArrayList<>();
            for (Long employeeId : projectDTO.getEmployeeIds()) {
                Employee employee = employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));
                employees.add(employee);
            }
            project.setEmployees(employees);
        }

        return project;
    }

    public WeeklyReport convertToWeeklyReportEntity(WeeklyReportDTO reportDTO) {
        Employee employee = employeeRepository.findById(reportDTO.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));

        Project project = projectRepository.findById(reportDTO.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid project ID"));

        WeeklyReport report = new WeeklyReport();
        report.setEmployee(employee);
        report.setProject(project);
//        report.setPlannedCompletionDate(reportDTO.getPlannedCompletionDate());
//        report.setActualCompletionDate(reportDTO.getActualCompletionDate());
//        report.setDeliverables(reportDTO.getDeliverables());
//        report.setNoOfHours(reportDTO.getNoOfHours());
//        report.setRemark(reportDTO.getRemark());
//        report.setActivity(reportDTO.getActivity());
//        report.setPointsForDiscussion(reportDTO.getPointsForDiscussion());
//        report.setExpectedActivitiesOfUpcomingWeek(reportDTO.getExpectedActivitiesOfUpcomingWeek());
//        report.setReportStatus(reportDTO.getReportStatus());
        return report;
    }




}
