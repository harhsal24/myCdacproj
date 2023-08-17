package com.hb.wrs.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class WeeklyReportDTO {
    private Long reportId;
    private EmployeeDTO employee;
    private ProjectDTO project;
    private Date plannedCompletionDate;
    private Date actualCompletionDate;
    private String deliverables;
    private LocalDateTime reportCreatedDateTime;
}

