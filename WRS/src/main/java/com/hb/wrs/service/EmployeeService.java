package com.hb.wrs.service;

import com.hb.wrs.dto.EmployeeDTO;
import com.hb.wrs.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long empId);

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployee(Long empId, EmployeeDTO employeeDTO);

    void deleteEmployee(Long empId);
}
