
package com.hb.wrs.dto;

import com.hb.wrs.util.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
    private Long empId;
    private String name;
    private Long managerId; // Use this to map manager's empId
    private Role role; // Use the Role enum
    // Other fields, getters, setters
}
