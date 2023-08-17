package com.hb.wrs.dto;

import com.hb.wrs.model.Employee;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
    private Long empId;
    private String name;
    private String designation;
    private Employee.Role role;
}

