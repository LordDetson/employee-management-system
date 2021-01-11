package by.babanin.ems.controller;

import by.babanin.ems.model.Employee;
import by.babanin.ems.resource.EmployeeResource;
import by.babanin.ems.service.CrudService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmployeeController {

    private final CrudService<Employee> employeeService;

    public EmployeeController(CrudService<Employee> employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String showIndex(Model model) {
        List<Employee> employees = employeeService.getAll();
        if (employees.isEmpty()) {
            model.addAttribute("warningMassage", EmployeeResource.EMPTY_LIST.get());
        }
        model.addAttribute("employees", employees);
        return "index";
    }
}
