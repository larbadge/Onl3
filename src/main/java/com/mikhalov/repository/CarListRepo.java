package com.mikhalov.repository;

import com.mikhalov.model.Car;
import com.mikhalov.model.Color;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CarListRepo extends CarRepository {

    private final List<Car> cars = new ArrayList<>();

    @Override
    public void updateColor(String id, Color color) {
        getById(id).ifPresent(car -> car.setColor(color));
    }

    @Override
    public void sortById() {
        cars.sort(Comparator.comparing(Car::getId));
    }

    @Override
    public int getCarIndex(Car car) {
        return cars.indexOf(car);
    }

    @Override
    public void save(Car car) {
        cars.add(car);
    }

    @Override
    public void insert(Car car, int index) {
        cars.add(index, car);
    }

    @Override
    public Optional<Car> getById(String id) {
        return cars.stream()
                .filter(car -> car.getId().equals(id))
                .findAny();
    }

    @Override
    public List<Car> getAll() {
        return cars;
    }

    @Override
    public void delete(String id) {
        getById(id).ifPresent(cars::remove);
    }

    @Override
    public void deleteAll() {
        cars.clear();
    }
}
