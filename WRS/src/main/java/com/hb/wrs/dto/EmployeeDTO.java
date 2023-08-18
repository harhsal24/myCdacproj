package com.hb.wrs.dto;

import com.hb.wrs.util.Gender;
import com.hb.wrs.util.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDTO {
    private Long empId;
    private String name;
    private String designation;
    private String email;
    private String password;
    private Role role;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private LocalDate joiningDate;
    private Long managerId;
}