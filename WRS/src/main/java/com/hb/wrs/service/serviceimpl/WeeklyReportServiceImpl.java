package com.hb.wrs.service.serviceimpl;

import java.util.List;
import java.util.NoSuchElementException;

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
    public WeeklyReport updateWeeklyReport(Long reportId, WeeklyReport updatedReport) {
        WeeklyReport existingReport = weeklyReportRepository.findById(reportId)
                .orElseThrow(() -> new NoSuchElementException("Weekly Report not found"));

        existingReport.setPlannedCompletionDate(updatedReport.getPlannedCompletionDate());
        existingReport.setActualCompletionDate(updatedReport.getActualCompletionDate());
        existingReport.setDeliverables(updatedReport.getDeliverables());

        return weeklyReportRepository.save(existingReport);
    }
}

