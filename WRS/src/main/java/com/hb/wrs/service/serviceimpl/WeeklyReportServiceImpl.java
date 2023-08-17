package com.hb.wrs.service.serviceimpl;

import java.util.List;
import java.util.NoSuchElementException;

import com.hb.wrs.dto.WeeklyReportDTO;
import com.hb.wrs.model.Project;
import com.hb.wrs.model.WeeklyReport;
import com.hb.wrs.repository.ProjectRepository;
import com.hb.wrs.repository.WeeklyReportRepository;
import com.hb.wrs.service.WeeklyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeeklyReportServiceImpl implements WeeklyReportService {

    @Autowired
    private WeeklyReportRepository weeklyReportRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<WeeklyReport> getAllReportsOrderByDateDesc() {
        return weeklyReportRepository.findAllByOrderByReportCreatedDateTimeDesc();
    }

    @Override
    public List<WeeklyReport> getAllReportsByEmployeeId(Long employeeId) {
        return weeklyReportRepository.findByEmployeeEmpIdOrderByReportCreatedDateTimeDesc(employeeId);
    }

    @Override
    public List<WeeklyReport> getReportsByTeamLeaderIdOrderByReportCreatedDateTimeDesc(Long teamLeaderId) {
        return weeklyReportRepository.findByProjectTeamLeaderEmpIdOrderByReportCreatedDateTimeDesc(teamLeaderId);
    }

    @Override
    public List<WeeklyReport> getReportsByEmployeeIdOrderByReportCreatedDateTimeDesc(Long employeeId) {
        return weeklyReportRepository.findByEmployeeEmpIdOrderByReportCreatedDateTimeDesc(employeeId);
    }

    @Override
    public List<WeeklyReport> getReportsByProjectIdOrderByReportCreatedDateTimeDesc(Long projectId) {
        return weeklyReportRepository.findByProjectProjectIdOrderByReportCreatedDateTimeDesc(projectId);
    }

    @Override
    public WeeklyReport createWeeklyReport(WeeklyReport weeklyReport) {
        Project project = weeklyReport.getProject();

        if (project != null && project.getProjectId() == null) {
            projectRepository.save(project);
            weeklyReport.setProject(project);
        }

        return weeklyReportRepository.save(weeklyReport);
    }

    @Override
    public void deleteWeeklyReport(Long reportId) {
        weeklyReportRepository.deleteById(reportId);
    }

    @Override
    public WeeklyReport updateWeeklyReport(Long reportId, WeeklyReportDTO updatedReportDTO) {
        WeeklyReport existingReport = weeklyReportRepository.findById(reportId)
                .orElseThrow(() -> new NoSuchElementException("Weekly Report not found"));

        // Update the existing report with data from the DTO
        existingReport.setPlannedCompletionDate(updatedReportDTO.getPlannedCompletionDate());
        existingReport.setActualCompletionDate(updatedReportDTO.getActualCompletionDate());
        existingReport.setDeliverables(updatedReportDTO.getDeliverables());

        // You might need to handle updating other properties here

        return weeklyReportRepository.save(existingReport);
    }



    @Override
    public WeeklyReport getWeeklyReportById(Long reportId) {
        return weeklyReportRepository.findById(reportId).orElse(null);
    }

}

