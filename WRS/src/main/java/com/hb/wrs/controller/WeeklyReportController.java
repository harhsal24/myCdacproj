package com.hb.wrs.controller;

import com.hb.wrs.dto.WeeklyReportDTO;
import com.hb.wrs.model.WeeklyReport;
import com.hb.wrs.service.DTOConverterService;
import com.hb.wrs.service.WeeklyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class WeeklyReportController {

    @Autowired
    private WeeklyReportService weeklyReportService;

    @Autowired
    private DTOConverterService dtoConverterService;

    @GetMapping
    public List<WeeklyReport> getAllReportsOrderByDateDesc() {
        return weeklyReportService.getAllReportsOrderByDateDesc();
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<WeeklyReport>> getReportsByEmployeeId(@PathVariable Long employeeId) {
        List<WeeklyReport> reports = weeklyReportService.getAllReportsByEmployeeId(employeeId);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/team-leader/{teamLeaderId}")
    public ResponseEntity<List<WeeklyReport>> getReportsByTeamLeaderId(@PathVariable Long teamLeaderId) {
        List<WeeklyReport> reports = weeklyReportService.getReportsByTeamLeaderIdOrderByReportCreatedDateTimeDesc(teamLeaderId);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<WeeklyReport>> getReportsByProjectId(@PathVariable Long projectId) {
        List<WeeklyReport> reports = weeklyReportService.getReportsByProjectIdOrderByReportCreatedDateTimeDesc(projectId);
        return ResponseEntity.ok(reports);
    }

    @PostMapping()
    public ResponseEntity<WeeklyReport> createWeeklyReport(@RequestBody WeeklyReportDTO weeklyReportDTO) {
        WeeklyReport weeklyReport = dtoConverterService.convertToWeeklyReportEntity(
                weeklyReportDTO, weeklyReportDTO.getEmployee(), weeklyReportDTO.getProject());
        WeeklyReport createdReport = weeklyReportService.createWeeklyReport(weeklyReport);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReport);
    }

    @PutMapping("/{reportId}")
    public ResponseEntity<WeeklyReport> updateWeeklyReport(
            @PathVariable Long reportId, @RequestBody WeeklyReport updatedReport) {
        WeeklyReport updated = weeklyReportService.updateWeeklyReport(reportId, updatedReport);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<Void> deleteWeeklyReport(@PathVariable Long reportId) {
        weeklyReportService.deleteWeeklyReport(reportId);
        return ResponseEntity.noContent().build();
    }
}

