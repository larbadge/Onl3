package com.mikhalov.repository;

import com.mikhalov.model.Car;
import com.mikhalov.model.Color;
import com.mikhalov.util.AlgorithmUtil;
import lombok.NonNull;

import java.util.*;

public class CarArrayRepository extends CarRepository {

    private static Car[] cars = new Car[3];

    @Override
    public void save(@NonNull Car car) {
        increaseArrayIfNeeds();
        putCar(car);
    }

    @Override
    public void insert(Car car, int index) {
        int length = findLength();
        increaseArrayIfNeeds();
        if (index >= length) {
            putCar(car);
        } else {
            System.arraycopy(cars, index, cars, index + 1, length - index);
            cars[index] = car;
        }
    }

    @Override
    public Optional<Car> getById(String id) {
        int index = indexById(id);
        if (index == -1) {
            return Optional.empty();
        } else return Optional.of(cars[index]);
    }

    @Override
    public List<Car> getAll() {
        return List.of(Arrays.copyOf(cars, findLength()));
    }

    @Override
    public void updateColor(String id, Color color) {
        int index = indexById(id);
        if (index == -1) {
            throw new IllegalArgumentException("Wrong ID");
        }
        cars[index].setColor(color);
    }

    @Override
    public void delete(String id) {
        int index = indexById(id);
        if (index == -1) {
            throw new IllegalArgumentException("Wrong ID");
        }
        int length = findLength();
        delete(index, length);
    }

    @Override
    public void deleteAll() {
        for (Car car : getAll()) {
            delete(car.getId());

        }
    }

    @Override
    public void sortById() {
        AlgorithmUtil.mergeSort(cars, Comparator.nullsLast(Comparator.comparing(Car::getId)));
    }

    @Override
    public int getCarIndex(Car car) {
        return indexById(car.getId());
    }

    public int sortAndGetCarIndex(Car car) {
        sortById();
        return AlgorithmUtil.binarySearch(cars, car, Comparator.nullsLast(Comparator.comparing(Car::getId)));
    }

    private void delete(int index, int length) {
        if (index == length - 1) {
            cars[index] = null;
        } else if (cars.length == length) {
            shiftArray(index);
            cars[length - 1] = null;
        } else {
            shiftArray(index);
        }
    }

    private void shiftArray(int index) {
        System.arraycopy(cars, index + 1, cars, index,
                cars.length - (index + 1));
    }

    private void increaseArrayIfNeeds() {
        if (findLength() == cars.length) {
            increaseArray();
        }
    }

    private int findLength() {
        int length = 0;
        for (Car car : cars) {
            if (car == null) {
                break;
            }
            length++;
        }
        return length;
    }

    private int indexById(String id) {
        int index = 0;
        for (Car car : getAll()) {
            if (car.getId().equals(id)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private void putCar(Car car) {
        cars[findLength()] = car;
    }

    private void increaseArray() {
        cars = Arrays.copyOf(cars, cars.length * 2);
    }
}
