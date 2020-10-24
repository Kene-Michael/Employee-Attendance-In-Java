package com.employeeattendance.demo.services.serviceImpl;

import com.employeeattendance.demo.models.Employee;
import com.employeeattendance.demo.repositories.EmployeeRepository;
import com.employeeattendance.demo.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<Employee> getEmployee(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long employee_id, Employee employee2) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employee_id);
        Employee employee = employeeOptional.get();
        if(employee2.getFirstName() != null && !employee2.getFirstName().isBlank()) employee.setFirstName(employee2.getFirstName());
        if(employee2.getLastName() != null && !employee2.getLastName().isBlank()) employee.setLastName(employee2.getLastName());
        if(employee2.getEmail() != null && !employee2.getEmail().isBlank()) employee.setEmail(employee2.getEmail());
        if(employee2.getPassword() != null && !employee2.getPassword().isBlank()) employee.setPassword(employee2.getPassword());
        if(employee2.getRole() != null && !employee2.getRole().isBlank()) employee.setRole(employee2.getRole());
        if(employee2.getPhoneNo() != null && !employee2.getPhoneNo().isBlank()) employee.setPhoneNo(employee2.getPhoneNo());
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public void deleteEmployee(Long employee_id) {
        employeeRepository.deleteById(employee_id);
    }
}
