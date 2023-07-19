package com.hb.wrs.repository;

import com.hb.wrs.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Additional custom methods can be defined here if needed
    
}