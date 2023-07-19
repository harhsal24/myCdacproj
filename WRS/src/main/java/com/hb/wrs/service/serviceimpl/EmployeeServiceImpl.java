package com.hb.wrs.service.serviceimpl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.wrs.model.Employee;
import com.hb.wrs.repository.EmployeeRepository;
import com.hb.wrs.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
     public Employee updateEmployee(Long employeeId, Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new NoSuchElementException("Employee not found"));
    
        // Update the fields of the existing employee
        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setDesignation(updatedEmployee.getDesignation());
        existingEmployee.setRole(updatedEmployee.getRole());
    
        return employeeRepository.save(existingEmployee);
    }
}
