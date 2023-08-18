
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
    private Long teamLeaderId; // Use this to map team leader's empId
    private List<Long> employeeIds; // Use this to map employee's empIds
    // Other fields, getters, setters
}
