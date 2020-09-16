package com.employeeattendance.demo.services;

import com.employeeattendance.demo.models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {
    Optional<Employee> getEmployee(Long id);
    List<Employee> getEmployees();
    Employee getEmployee(String email);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long employee_id, Employee employee2);
    void deleteEmployee(Long employee_id);
}
