package com.hb.wrs.service.serviceimpl;

import com.hb.wrs.dto.WeeklyReportDTO;
import com.hb.wrs.model.Employee;
import com.hb.wrs.model.Project;
import com.hb.wrs.model.WeeklyReport;
import com.hb.wrs.repository.EmployeeRepository;
import com.hb.wrs.repository.ProjectRepository;
import com.hb.wrs.repository.WeeklyReportRepository;
import com.hb.wrs.service.WeeklyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class WeeklyReportServiceImpl implements WeeklyReportService {

    @Autowired
    private WeeklyReportRepository weeklyReportRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<WeeklyReportDTO> getAllReportsOrderByDateDesc() {
        List<WeeklyReport> weeklyReports = weeklyReportRepository.findAllByOrderByReportCreatedDateTimeDesc();
        return weeklyReports.stream()
                .map(this::mapWeeklyReportToDTO)
                .collect(Collectors.toList());
    }




    @Override
    public List<WeeklyReportDTO> getReportsByEmployeeIdOrderByReportCreatedDateTimeDesc(Long employeeId) {
        System.out.println("getReportsByEmployeeIdOrderByReportCreatedDateTimeDesc method invoked with employeeId: " + employeeId);
        List<WeeklyReport> reports = weeklyReportRepository.findByEmployeeEmpIdOrderByReportCreatedDateTimeDesc(employeeId);

        // Log the content of the reports list
        System.out.println("Reports list for employeeId " + employeeId + ": " + reports);

        return reports.stream()
                .map(this::mapWeeklyReportToDTO)
                .collect(Collectors.toList());
    }



    @Override
    public List<WeeklyReportDTO> getReportsByTeamLeaderIdOrderByReportCreatedDateTimeDesc(Long teamLeaderId) {
        List<WeeklyReport> reports = weeklyReportRepository.findByProjectTeamLeaderEmpIdOrderByReportCreatedDateTimeDesc(teamLeaderId);
        return reports.stream()
                .map(this::mapWeeklyReportToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<WeeklyReportDTO> getReportsByProjectIdOrderByReportCreatedDateTimeDesc(Long projectId) {
        List<WeeklyReport> reports = weeklyReportRepository.findByProjectProjectIdOrderByReportCreatedDateTimeDesc(projectId);
        return reports.stream()
                .map(this::mapWeeklyReportToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public WeeklyReportDTO createWeeklyReport(WeeklyReportDTO weeklyReportDTO) {
        WeeklyReport weeklyReport = mapDTOToWeeklyReport(weeklyReportDTO);

        // Fetch the employee using the employeeId from the DTO
        Employee employee = employeeRepository.findById(weeklyReportDTO.getEmployeeId())
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
        weeklyReport.setEmployee(employee);

        Project project = weeklyReport.getProject();

        if (project != null && project.getProjectId() == null) {
            projectRepository.save(project);
            weeklyReport.setProject(project);
        }

        WeeklyReport createdReport = weeklyReportRepository.save(weeklyReport);
        return mapWeeklyReportToDTO(createdReport);
    }



    @Override
    public void deleteWeeklyReport(Long reportId) {
        weeklyReportRepository.deleteById(reportId);
    }

    @Override
    public WeeklyReportDTO updateWeeklyReport(Long reportId, WeeklyReportDTO updatedReportDTO) {
        WeeklyReport existingReport = weeklyReportRepository.findById(reportId)
                .orElseThrow(() -> new NoSuchElementException("Weekly Report not found"));

        // Update the existing report with data from the DTO
//        existingReport.setPlannedCompletionDate(updatedReportDTO.getPlannedCompletionDate());
//        existingReport.setActualCompletionDate(updatedReportDTO.getActualCompletionDate());
//        existingReport.setDeliverables(updatedReportDTO.getDeliverables());
//        existingReport.setNoOfHours(updatedReportDTO.getNoOfHours());
//        existingReport.setRemark(updatedReportDTO.getRemark());
//        existingReport.setActivity(updatedReportDTO.getActivity());
//        existingReport.setPointsForDiscussion(updatedReportDTO.getPointsForDiscussion());
//        existingReport.setExpectedActivitiesOfUpcomingWeek(updatedReportDTO.getExpectedActivitiesOfUpcomingWeek());
//        existingReport.setReportStatus(updatedReportDTO.getReportStatus());

        WeeklyReport updatedReport = weeklyReportRepository.save(existingReport);
        return mapWeeklyReportToDTO(updatedReport);
    }

    @Override
    public WeeklyReportDTO getWeeklyReportById(Long reportId) {
        WeeklyReport report = weeklyReportRepository.findById(reportId).orElse(null);
        if (report != null) {
            return mapWeeklyReportToDTO(report);
        }
        return null;
    }

    private WeeklyReportDTO mapWeeklyReportToDTO(WeeklyReport report) {
        WeeklyReportDTO reportDTO = new WeeklyReportDTO();

        if (report.getEmployee() != null) {
            reportDTO.setEmployeeId(report.getEmployee().getEmpId());
        }

        if (report.getProject() != null) {
            reportDTO.setProjectId(report.getProject().getProjectId());
        }

//        reportDTO.setPlannedCompletionDate(report.getPlannedCompletionDate());
//        reportDTO.setActualCompletionDate(report.getActualCompletionDate());
//        reportDTO.setDeliverables(report.getDeliverables());
//        reportDTO.setNoOfHours(report.getNoOfHours());
//        reportDTO.setRemark(report.getRemark());
//        reportDTO.setActivity(report.getActivity());
//        reportDTO.setPointsForDiscussion(report.getPointsForDiscussion());
//        reportDTO.setExpectedActivitiesOfUpcomingWeek(report.getExpectedActivitiesOfUpcomingWeek());
//        reportDTO.setReportStatus(report.getReportStatus());

        return reportDTO;
    }


    private WeeklyReport mapDTOToWeeklyReport(WeeklyReportDTO reportDTO) {
        WeeklyReport report = new WeeklyReport();
        // You may need to populate other properties here
        return report;
    }
}
