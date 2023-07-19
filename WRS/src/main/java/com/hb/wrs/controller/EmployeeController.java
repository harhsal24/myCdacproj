package com.hb.wrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hb.wrs.model.Employee;
import com.hb.wrs.service.EmployeeService;
import com.hb.wrs.service.serviceimpl.EmployeeServiceImpl;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        System.out.println(employee);
        Employee createdEmployee = employeeServiceImpl.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long employeeId, @RequestBody Employee updatedEmployee) {
        Employee updated = employeeServiceImpl.updateEmployee(employeeId, updatedEmployee);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) {
        employeeServiceImpl.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }
    
}
