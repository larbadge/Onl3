package com.mikhalov.repository;

import com.mikhalov.model.Car;
import com.mikhalov.model.Color;
import lombok.NonNull;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class CarArrayRepository {

    private static Car[] cars = new Car[3];

    public void save(@NonNull Car car) {
        increaseArrayIfNeeds();
        putCar(car);
    }

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

    public Car getById(String id) {
        return cars[indexById(id)];
    }


    public Car[] getAll() {
        return Arrays.copyOf(cars, findLength());
    }

    public void updateColor(String id, Color color) {
        int index = indexById(id);
        cars[index].setColor(color);
    }

    public void delete(String id) {
        int index = indexById(id);
        int length = findLength();
        delete(index, length);
    }

    public void deleteAll() {
        for (Car car : getAll()) {
            delete(car.getId());

        }
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
        throw new NoSuchElementException("Wrong ID");
    }

    private void putCar(Car car) {
        cars[findLength()] = car;
    }

    private void increaseArray() {
        cars = Arrays.copyOf(cars, cars.length * 2);
    }
}
