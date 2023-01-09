package com.mikhalov.repository;

import com.mikhalov.model.Car;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    void save(T t);

    void insert(T t, int index);

    Optional<T> getById(String id);

    List<Car> getAll();

    void delete(String id);

    void deleteAll();

}
