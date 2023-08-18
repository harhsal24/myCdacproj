package com.hb.wrs.service;

import com.hb.wrs.dto.WeeklyReportDTO;

import java.util.List;

public interface WeeklyReportService {

    List<WeeklyReportDTO> getAllReportsOrderByDateDesc();

    List<WeeklyReportDTO> getReportsByEmployeeIdOrderByReportCreatedDateTimeDesc(Long employeeId);

    List<WeeklyReportDTO> getReportsByTeamLeaderIdOrderByReportCreatedDateTimeDesc(Long teamLeaderId);

    List<WeeklyReportDTO> getReportsByProjectIdOrderByReportCreatedDateTimeDesc(Long projectId);

    WeeklyReportDTO createWeeklyReport(WeeklyReportDTO weeklyReportDTO);

    void deleteWeeklyReport(Long reportId);

    WeeklyReportDTO updateWeeklyReport(Long reportId, WeeklyReportDTO updatedReportDTO);

    WeeklyReportDTO getWeeklyReportById(Long reportId);
}
