package com.employeeattendance.demo.controllers;

import com.employeeattendance.demo.models.Employee;
import com.employeeattendance.demo.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/load")
    public String load(Model model, HttpSession httpSession) {
        Employee employee = (Employee) httpSession.getAttribute("employee");
        if(employee == null) return "redirect:/";
        return "redirect:/attendance/load";
    }

    @GetMapping("/create")
    public String loadCreate(Model model, HttpSession httpSession) {
        Employee admin = (Employee) httpSession.getAttribute("employee");
        if(admin == null || !admin.getRole().equalsIgnoreCase("admin")) return "redirect:/";
        model.addAttribute("employee", new Employee());
        model.addAttribute("invalid", null);
        return "createEmployee";
    }

    @PostMapping("/create")
    public String createEmployee(HttpSession httpSession, @Valid Employee employee,Model model) {
        Employee admin = (Employee) httpSession.getAttribute("employee");
        if (admin == null) return "redirect:/";
        System.out.println(employee.getPhoneNo().length());
        if(employee.getPhoneNo().length() != 11) {
            model.addAttribute("invalid","phone number not valid");
            return "createEmployee";
        }
        employeeService.createEmployee(employee);
        return "redirect:/employee/create";
    }

    @GetMapping("/employees")
    public String getEmployees(Model model, HttpSession httpSession) {
        Object employeeObj = httpSession.getAttribute("employee");
        if (employeeObj == null) return "redirect:/";
        model.addAttribute("employee",(Employee) employeeObj);
        model.addAttribute("employees",employeeService.getEmployees());
        return "viewemployees";
    }

    @GetMapping("/view/{employee_id}")
    public String getEmployee(@PathVariable Long employee_id, HttpSession httpSession,Model model) {
        Object employeeObj = httpSession.getAttribute("employee");
        if (employeeObj == null) return "redirect:/";
        Optional<Employee> employeeOptional = employeeService.getEmployee(employee_id);
        if(employeeOptional.isEmpty()) return null;
        Employee employee = employeeOptional.get();
        model.addAttribute("employee",employee);
        return "employeebio";
    }

    @PostMapping("/view/{employee_id}")
    public String updateEmployee(@Valid Employee employee, HttpSession httpSession, @PathVariable Long employee_id) {
        Employee admin = (Employee) httpSession.getAttribute("employee");
        if (admin == null) return "redirect:/";
        employeeService.updateEmployee(employee_id,employee);
        return "redirect:/employee/view/" + employee_id;
    }

    @PostMapping("/delete/{employee_id}")
    public String deleteEmployee(@Valid Employee employee,HttpSession httpSession, @PathVariable Long employee_id) {
        Employee admin = (Employee) httpSession.getAttribute("employee");
        if (admin == null) return "redirect:/";
        employeeService.deleteEmployee(employee_id);
        return "redirect:/employee/employees";
    }
}
