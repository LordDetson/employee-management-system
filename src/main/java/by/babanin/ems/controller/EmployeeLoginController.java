package by.babanin.ems.controller;

import by.babanin.ems.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class EmployeeLoginController {

    @ModelAttribute("employee")
    public Employee createEmptyEmployee() {
        return new Employee();
    }

    @GetMapping
    public String showLogin() {
        return "login";
    }
}
