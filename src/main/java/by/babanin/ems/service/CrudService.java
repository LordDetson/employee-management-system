package by.babanin.ems.service;

import java.util.List;

public interface CrudService<T> {

    List<T> getAll();

    long count();

}
