
package com.hb.wrs.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProjectDTO {
    private Long projectId;
    private String projectName;
    private String projectType;
    private LocalDate startDate;
    private LocalDate expectedEndDate;
    private Long teamLeaderId;
    private List<Long> employeeIds;
}
