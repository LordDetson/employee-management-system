package by.babanin.ems.service;

import java.util.List;

public interface CrudService<T, ID> {

    List<T> getAll();

    T getById(ID id);

    long count();

    void create(T element);

    T update(ID id, T element);
}
