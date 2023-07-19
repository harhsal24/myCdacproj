package com.hb.wrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.wrs.model.Project;
import com.hb.wrs.model.WeeklyReport;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    List<Project> findByTeamLeaderEmpId(Long teamLeaderId);
    List<Project> findByEmployeesEmpId(Long employeeId);
   
}