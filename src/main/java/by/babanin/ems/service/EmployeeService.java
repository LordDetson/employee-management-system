package by.babanin.ems.service;

import by.babanin.ems.model.Employee;
import by.babanin.ems.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements CrudService<Employee> {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public long count() {
        return employeeRepository.count();
    }

    @Override
    public void create(Employee employee) {
        Employee employeeToSave = Employee.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
        employeeRepository.save(employeeToSave);
    }
}
