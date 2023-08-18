package com.hb.wrs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hb.wrs.model.WeeklyReport;

public interface WeeklyReportRepository extends JpaRepository<WeeklyReport, Long> {



    @Query("SELECT r FROM WeeklyReport r ORDER BY r.reportCreatedDateTime DESC")
    List<WeeklyReport> findAllByOrderByReportCreatedDateTimeDesc();

    List<WeeklyReport> findByEmployeeEmpIdOrderByReportCreatedDateTimeDesc(Long employeeId);

    List<WeeklyReport> findByProjectTeamLeaderEmpIdOrderByReportCreatedDateTimeDesc(Long teamLeaderId);

    List<WeeklyReport> findByProjectProjectIdOrderByReportCreatedDateTimeDesc(Long projectId);
}
