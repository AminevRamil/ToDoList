package com.epam.starbun.todolist.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//public interface CommonRepository<T> extends CrudRepository<T, UUID>  ??

public interface CommonRepository<T> {

    Optional<T> findById(UUID id);

    List<T> findAll();

    T save(T entity);

    void delete(T entity);

}
