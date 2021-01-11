package by.babanin.ems.service;

import by.babanin.ems.exception.ResourceNotFoundException;
import by.babanin.ems.model.Employee;
import by.babanin.ems.repository.EmployeeRepository;
import by.babanin.ems.resource.EmployeeResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements CrudService<Employee, Long> {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(EmployeeResource.NOT_EXIST.format(id)));
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

    @Override
    public Employee update(Long id, Employee element) {
        Employee employeeToSave = getById(id);
        employeeToSave.setFirstName(element.getFirstName());
        employeeToSave.setLastName(element.getLastName());
        employeeToSave.setEmail(element.getEmail());
        return employeeRepository.save(element);
    }
}
