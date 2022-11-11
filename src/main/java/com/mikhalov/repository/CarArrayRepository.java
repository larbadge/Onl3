package com.mikhalov.repository;

import com.mikhalov.model.Car;
import com.mikhalov.model.Color;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class CarArrayRepository {

    private static Car[] cars = new Car[10];

    public void save(Car car) {
        increaseArrayIfNeeds();
        putCar(car);
    }

    public void insert(Car car, int index) {
        int length = findLength();
        increaseArrayIfNeeds();
        if (index >= length) {
            putCar(car);
        } else {
            System.arraycopy(cars, index, cars, index + 1,
                    length - index);
            cars[index] = car;
        }
    }

    public Car getById(String id) {
        for (Car car : cars) {
            if (car.getId().equals(id)) {
                return car;
            }
        }
        return null;
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

    private void delete(int index, int length) {
        if (index == cars.length) {
            throw new NoSuchElementException("Car with this ID doesn`t exist");
        } else if (index == length - 1) {
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
        int length = findLength();
        if (length == cars.length) {
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
        for (Car car : cars) {
            if (car.getId().equals(id)) {
                break;
            }
            index++;
        }
        return index;
    }

    private void putCar(Car car) {
        int length = findLength();
        cars[length] = car;
    }

    private void increaseArray() {
        cars = Arrays.copyOf(cars, cars.length * 2);
    }
}
