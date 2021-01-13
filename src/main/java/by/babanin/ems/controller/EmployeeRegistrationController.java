package by.babanin.ems.controller;

import by.babanin.ems.model.Employee;
import by.babanin.ems.model.Role;
import by.babanin.ems.service.CrudService;
import by.babanin.ems.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/registration")
public class EmployeeRegistrationController {

    private final CrudService<Employee, Long> employeeService;

    public EmployeeRegistrationController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ModelAttribute("employee")
    public Employee createEmptyEmployee() {
        return new Employee();
    }

    @GetMapping
    public String showRegistrationPage() {
        return "registration";
    }

    @PostMapping
    public String register(@ModelAttribute Employee employee) {
        employee.setRoles(Collections.singleton(Role.USER));
        employeeService.create(employee);
        return "redirect:/registration?success";
    }
}
