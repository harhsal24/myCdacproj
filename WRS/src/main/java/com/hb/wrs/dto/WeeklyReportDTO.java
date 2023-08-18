package com.hb.wrs.dto;

import com.hb.wrs.util.ReportStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class WeeklyReportDTO {
    private Long reportId;
    private Long employeeId; // Use this to map employee's empId
    private Long projectId; // Use this to map project's projectId
    private LocalDateTime reportCreatedDateTime;
    // Other fields, getters, setters
}
