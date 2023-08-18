package com.hb.wrs.controller;

import com.hb.wrs.dto.WeeklyReportDTO;
import com.hb.wrs.model.WeeklyReport;
import com.hb.wrs.service.WeeklyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class WeeklyReportController {

    @Autowired
    private WeeklyReportService weeklyReportService;

    @GetMapping
    public ResponseEntity<List<WeeklyReportDTO>> getAllReportsOrderByDateDesc() {
        List<WeeklyReportDTO> reports = weeklyReportService.getAllReportsOrderByDateDesc();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<WeeklyReportDTO>> getReportsByEmployeeId(@PathVariable Long employeeId) {
        List<WeeklyReportDTO> reports = weeklyReportService.getReportsByEmployeeIdOrderByReportCreatedDateTimeDesc(employeeId);
        return ResponseEntity.ok(reports);
    }


    @GetMapping("/teamleader/{teamLeaderId}")
    public ResponseEntity<List<WeeklyReportDTO>> getReportsByTeamLeaderId(@PathVariable Long teamLeaderId) {
        List<WeeklyReportDTO> reports = weeklyReportService.getReportsByTeamLeaderIdOrderByReportCreatedDateTimeDesc(teamLeaderId);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<WeeklyReportDTO>> getReportsByProjectId(@PathVariable Long projectId) {
        List<WeeklyReportDTO> reports = weeklyReportService.getReportsByProjectIdOrderByReportCreatedDateTimeDesc(projectId);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<WeeklyReportDTO> getReportById(@PathVariable Long reportId) {
        WeeklyReportDTO report = weeklyReportService.getWeeklyReportById(reportId);
        if (report != null) {
            return ResponseEntity.ok(report);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<WeeklyReportDTO> createWeeklyReport(@RequestBody WeeklyReportDTO weeklyReportDTO) {
        WeeklyReportDTO createdReport = weeklyReportService.createWeeklyReport(weeklyReportDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReport);
    }

    @PutMapping("/{reportId}")
    public ResponseEntity<WeeklyReportDTO> updateWeeklyReport(
            @PathVariable Long reportId, @RequestBody WeeklyReportDTO updatedReportDTO) {
        WeeklyReportDTO updatedReport = weeklyReportService.updateWeeklyReport(reportId, updatedReportDTO);
        return ResponseEntity.ok(updatedReport);
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<Void> deleteWeeklyReport(@PathVariable Long reportId) {
        weeklyReportService.deleteWeeklyReport(reportId);
        return ResponseEntity.noContent().build();
    }
}
