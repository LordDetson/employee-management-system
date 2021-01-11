package by.babanin.ems.service;

import by.babanin.ems.model.Employee;

import java.util.List;

public interface CrudService {

    List<Employee> getAll();

    long count();

}
