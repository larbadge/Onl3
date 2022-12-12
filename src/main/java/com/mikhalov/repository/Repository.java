package com.mikhalov.repository;

public interface Repository<T> {

    void save(T t);

    void insert(T t, int index);

    T getById(String id);

    T[] getAll();

    void delete(String id);

    void deleteAll();

}
