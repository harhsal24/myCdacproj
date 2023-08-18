// EmployeeServiceImpl.java
package com.hb.wrs.service.serviceimpl;

import com.hb.wrs.dto.EmployeeDTO;
import com.hb.wrs.model.Employee;
import com.hb.wrs.repository.EmployeeRepository;
import com.hb.wrs.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::mapEmployeeToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long empId) {
        Employee employee = employeeRepository.findById(empId).orElse(null);
        if (employee != null) {
            return mapEmployeeToDTO(employee);
        }
        return null;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = mapDTOToEmployee(employeeDTO);
        Employee createdEmployee = employeeRepository.save(employee);
        return mapEmployeeToDTO(createdEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long empId, EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(empId).orElse(null);
        if (existingEmployee == null) {
            return null;
        }

        Employee updatedEmployee = mapDTOToEmployee(employeeDTO);
        updatedEmployee.setEmpId(empId);
        Employee savedEmployee = employeeRepository.save(updatedEmployee);
        return mapEmployeeToDTO(savedEmployee);
    }

    @Override
    public void deleteEmployee(Long empId) {
        employeeRepository.deleteById(empId);
    }

    private EmployeeDTO mapEmployeeToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmpId(employee.getEmpId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setManagerId(employee.getManager() != null ? employee.getManager().getEmpId() : null);
        // Map other properties as needed
        return employeeDTO;
    }

    private Employee mapDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        // Map other properties as needed
        return employee;
    }
}
