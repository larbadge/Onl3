package com.mikhalov.repository;
import com.mikhalov.model.Color;

public interface Repository<T> {

    void save(T t);

    void insert(T t, int index);

    T getById(String id);

    T[] getAll();

    void delete(String id);

    void deleteAll();

    void sortById();

    int searchById(String id);

    void updateColor(String id, Color color);

}
