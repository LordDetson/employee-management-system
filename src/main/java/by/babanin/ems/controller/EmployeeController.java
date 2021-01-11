package by.babanin.ems.controller;

import by.babanin.ems.model.Employee;
import by.babanin.ems.resource.EmployeeResource;
import by.babanin.ems.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String showIndex(Model model) {
        return showPage(1, 2, model);
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

    @GetMapping("/employee/update")
    public String showEmployeeUpdatingForm(@RequestParam Long id, Model model) {
        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        return "employee-updating-form";
    }

    @PostMapping("/employee/update")
    public String update(@RequestParam Long id, @ModelAttribute Employee employee) {
        employeeService.update(id, employee);
        return "redirect:/";
    }

    @GetMapping("/employee/delete")
    public String delete(@RequestParam Long id) {
        employeeService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/employee/page")
    public String showPage(
            @RequestParam int number,
            @RequestParam(required = false, defaultValue = "2") int size,
            Model model
    ) {
        Page<Employee> page = employeeService.getPage(number, size);
        List<Employee> employees = page.getContent();
        if (employees.isEmpty()) {
            model.addAttribute("warningMassage", EmployeeResource.EMPTY_LIST.get());
            return "index";
        }
        model.addAttribute("currentPage", number);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("employees", employees);
        return "index";
    }
}
