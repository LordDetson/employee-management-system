package by.babanin.ems.service;

import by.babanin.ems.exception.ResourceNotFoundException;
import by.babanin.ems.model.Employee;
import by.babanin.ems.repository.EmployeeRepository;
import by.babanin.ems.resource.EmployeeResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements CrudService<Employee, Long>, PagingService<Employee>, UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(EmployeeResource.NOT_EXIST.format("ID", id)));
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
                .password(encodePassword(employee.getPassword()))
                .roles(employee.getRoles())
                .build();
        return employeeRepository.save(employeeToSave);
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public Employee update(Long id, Employee employee) {
        Employee employeeToSave = getById(id);
        employeeToSave.setFirstName(employee.getFirstName());
        employeeToSave.setLastName(employee.getLastName());
        employeeToSave.setEmail(employee.getEmail());
        employeeToSave.setPassword(employee.getPassword());
        employeeToSave.setRoles(employee.getRoles());
        return employeeRepository.save(employee);
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(EmployeeResource.NOT_EXIST.format("Email", email)));
    }
}
