package com.hb.wrs.controller;

import com.hb.wrs.model.WeeklyReport;
import com.hb.wrs.service.WeeklyReportService;
import com.hb.wrs.service.serviceimpl.WeeklyReportServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class WeeklyReportController {

    @Autowired
    private  WeeklyReportServiceImpl weeklyReportServiceImpl;

   

    @GetMapping
    public List<WeeklyReport> getAllReportsOrderByDateDesc() {
        return weeklyReportServiceImpl.getAllReportsOrderByDateDesc();
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<WeeklyReport>> getReportsByEmployeeId(@PathVariable Long employeeId) {
        List<WeeklyReport> reports = weeklyReportServiceImpl.getAllReportsByEmployeeId(employeeId);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/team-leader/{teamLeaderId}")
    public ResponseEntity<List<WeeklyReport>> getReportsByTeamLeaderId(@PathVariable Long teamLeaderId) {
        List<WeeklyReport> reports = weeklyReportServiceImpl
                .getReportsByTeamLeaderIdOrderByReportCreatedDateTimeDesc(teamLeaderId);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<WeeklyReport>> getReportsByProjectId(@PathVariable Long projectId) {
        List<WeeklyReport> reports = weeklyReportServiceImpl
                .getReportsByProjectIdOrderByReportCreatedDateTimeDesc(projectId);
        return ResponseEntity.ok(reports);
    }

    // Create a weekly report
    @PostMapping("/reports")
    public ResponseEntity<WeeklyReport> createWeeklyReport(@RequestBody WeeklyReport weeklyReport) {
        WeeklyReport createdReport = weeklyReportServiceImpl.createWeeklyReport(weeklyReport);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReport);
    }

    // Update a weekly report
    @PutMapping("/reports/{reportId}")
    public ResponseEntity<WeeklyReport> updateWeeklyReport(
            @PathVariable Long reportId, @RequestBody WeeklyReport updatedReport) {
        WeeklyReport updated = weeklyReportServiceImpl.updateWeeklyReport(reportId, updatedReport);
        return ResponseEntity.ok(updated);
    }

    // Delete a weekly report
    @DeleteMapping("/reports/{reportId}")
    public ResponseEntity<Void> deleteWeeklyReport(@PathVariable Long reportId) {
        weeklyReportServiceImpl.deleteWeeklyReport(reportId);
        return ResponseEntity.noContent().build();
    }

}
