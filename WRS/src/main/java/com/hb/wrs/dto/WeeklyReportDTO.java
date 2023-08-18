package com.hb.wrs.dto;

import com.hb.wrs.util.ReportStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WeeklyReportDTO {
    private Long reportId;
    private Long employeeId;
    private Long projectId;
    private LocalDate plannedCompletionDate; // Change the data type
    private LocalDate actualCompletionDate; // Change the data type
    private String deliverables;
    private int noOfHours;
    private String remark;
    private String activity;
    private String pointsForDiscussion;
    private String expectedActivitiesOfUpcomingWeek;
    private ReportStatus reportStatus;
}