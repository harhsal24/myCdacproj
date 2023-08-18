package com.hb.wrs.service;

import com.hb.wrs.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long empId);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Long empId, Employee employee);

    void deleteEmployee(Long empId);
}
