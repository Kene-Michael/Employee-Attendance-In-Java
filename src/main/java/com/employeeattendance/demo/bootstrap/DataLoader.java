package com.employeeattendance.demo.bootstrap;

import com.employeeattendance.demo.models.Employee;
import com.employeeattendance.demo.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    EmployeeService employeeService;

    public DataLoader(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Employee employee = employeeService.getEmployee(1L).orElse(null);
        log.info("in onApp event");
        if (employee == null) {
            employee = new Employee();
            employee.setFirstName("Kenechukwu");
            employee.setLastName("Okafor");
            employee.setEmail("hello@gmail.com");
            employee.setPassword("hello");
            employee.setRole("Admin");
            employee.setPhoneNo("08138119774");

            employeeService.createEmployee(employee);
            log.info("created employee");
        }
    }
}
