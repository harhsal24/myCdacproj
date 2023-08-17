package com.hb.wrs.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectDTO {
    private Long projectId;
    private String projectName;
    private String projectType;
    private EmployeeDTO teamLeader;
    private List<EmployeeDTO> employees;
}

