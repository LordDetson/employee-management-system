package by.babanin.ems.controller;

import by.babanin.ems.model.Employee;
import by.babanin.ems.resource.EmployeeResource;
import by.babanin.ems.service.CrudService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/employee/create")
    public String showEmployeeCreationForm(Model model) {
        Employee emptyEmployee = new Employee();
        model.addAttribute("employee", emptyEmployee);
        return "employee-creation-form";
    }

    @PostMapping("/employee/create")
    public String create(@ModelAttribute Employee employee) {
        employeeService.create(employee);
        return "redirect:/";
    }
}
