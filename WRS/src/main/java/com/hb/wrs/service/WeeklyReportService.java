package com.hb.wrs.service;

import java.util.Date;
import java.util.List;

import com.hb.wrs.model.WeeklyReport;

public interface WeeklyReportService {

    List<WeeklyReport> getAllReportsOrderByDateDesc();

    List<WeeklyReport> getAllReportsByEmployeeId(Long employeeId);

    List<WeeklyReport> getReportsByEmployeeIdOrderByReportCreatedDateTimeDesc(Long employeeId);

    List<WeeklyReport> getReportsByTeamLeaderIdOrderByReportCreatedDateTimeDesc(Long teamLeaderId);

    List<WeeklyReport> getReportsByProjectIdOrderByReportCreatedDateTimeDesc(Long projectId);

    WeeklyReport createWeeklyReport(WeeklyReport weeklyReport);

    void deleteWeeklyReport(Long reportId);

    WeeklyReport updateWeeklyReport(Long reportId, WeeklyReport updatedReport);
}
