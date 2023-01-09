package com.mikhalov.repository;

import com.mikhalov.model.Car;
import com.mikhalov.model.Color;

import java.util.*;

public class CarMapRepo extends CarRepository {

    private Map<String, Car> cars = new LinkedHashMap<>();


    @Override
    public int getCarIndex(Car car) {
        int index = 0;
        String id = car.getId();
        for (String s : cars.keySet()) {
            if (s.equals(id)) {
                break;
            }
            index++;
        }
        return index;
    }

    @Override
    public void save(Car car) {
        cars.put(car.getId(), car);
    }

    @Override
    public void insert(Car car, int index) {
        LinkedHashMap<String, Car> newMap = new LinkedHashMap<>();
        cars.entrySet()
                .stream()
                .limit(index)
                .forEach((set) -> newMap.put(set.getKey(), set.getValue()));

        newMap.put(car.getId(), car);

        cars.entrySet()
                .stream()
                .skip(index)
                .forEach((set) -> newMap.put(set.getKey(), set.getValue()));
        cars = newMap;
    }

    @Override
    public Optional<Car> getById(String id) {
        return Optional.ofNullable(cars.get(id));
    }

    @Override
    public List<Car> getAll() {
        return List.copyOf(cars.values());
    }

    @Override
    public void delete(String id) {
        cars.remove(id);
    }

    @Override
    public void deleteAll() {
        cars.clear();
    }

    @Override
    public void updateColor(String id, Color color) {

    }

    @Override
    public void sortById() {
        cars = new LinkedHashMap<>(new TreeMap<>(cars));
    }
}
