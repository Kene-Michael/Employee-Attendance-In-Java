package com.employeeattendance.demo.controllers;

import com.employeeattendance.demo.models.Employee;
import com.employeeattendance.demo.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class SignInController {
    private EmployeeService employeeService;

    public SignInController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public String login(Model model,HttpSession httpSession) {
        Employee loggedEmployee = (Employee) httpSession.getAttribute("employee");
        if (loggedEmployee != null) {
                return "redirect:/employee/load";
        }
        model.addAttribute("employee", new Employee());
        model.addAttribute("invalid", null);
        return "signin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

    @PostMapping("")
    public String login(HttpSession httpSession, @Valid Employee employee, Model model) {
        Employee gottenEmployee = employeeService.getEmployee(employee.getEmail());
        if (gottenEmployee == null || !gottenEmployee.getPassword().equals(employee.getPassword())) {
            model.addAttribute("invalid", "Invalid email or password");
            return "signin";
        }
        httpSession.setAttribute("employee",gottenEmployee);
        return "redirect:/employee/load";
    }
}
