package com.hb.wrs.service;

import com.hb.wrs.model.Employee;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    void deleteEmployee(Long employeeId);

    Employee updateEmployee(Long employeeId, Employee updatedEmployee);
}
