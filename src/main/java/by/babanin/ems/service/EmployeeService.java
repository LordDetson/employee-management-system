package by.babanin.ems.service;

import by.babanin.ems.exception.ResourceNotFoundException;
import by.babanin.ems.model.Employee;
import by.babanin.ems.repository.EmployeeRepository;
import by.babanin.ems.resource.EmployeeResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements CrudService<Employee, Long>, PagingService<Employee> {

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
    public Employee create(Employee employee) {
        Employee employeeToSave = Employee.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
        return employeeRepository.save(employeeToSave);
    }

    @Override
    public Employee update(Long id, Employee element) {
        Employee employeeToSave = getById(id);
        employeeToSave.setFirstName(element.getFirstName());
        employeeToSave.setLastName(element.getLastName());
        employeeToSave.setEmail(element.getEmail());
        return employeeRepository.save(element);
    }

    @Override
    public void delete(Long id) {
        Employee employee = getById(id);
        employeeRepository.delete(employee);
    }

    @Override
    public Page<Employee> getPage(int number, int size, Sort.Direction direction, String fieldName) {
        PageRequest pageRequest = PageRequest.of(number - 1, size, direction, fieldName);
        return employeeRepository.findAll(pageRequest);
    }
}
