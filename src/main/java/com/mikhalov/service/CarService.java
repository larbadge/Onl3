package com.mikhalov.service;

import com.mikhalov.model.Car;
import com.mikhalov.model.Color;
import com.mikhalov.repository.CarArrayRepository;
import com.mikhalov.util.RandomGenerator;

public class CarService {

    private final CarArrayRepository carArrayRepository;

    public CarService(final CarArrayRepository carArrayRepository) {
        this.carArrayRepository = carArrayRepository;
    }

    public void create(int count) {
        for (; count > 0; count--) {
            carArrayRepository.save(createNewCar());
        }
    }

    public int create() {
        int count = RandomGenerator.generateRandomNum();
        if (count == 0) {
            return -1;
        }
        create(count);
        printAll();
        return count;
    }

    private Car createNewCar() {
        return new Car(RandomGenerator.getRandomString(),
                RandomGenerator.getRandomEngine(), RandomGenerator.getRandomColor());
    }

    public void insert(Car car, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index less than 0");
        }
        if (car != null) {
            carArrayRepository.insert(car, index);
        }
    }

    public Car find(String id)  {
        checkId(id);
        return carArrayRepository.getById(id);
    }

    public void delete(String id) {
        checkId(id);
        carArrayRepository.delete(id);
    }

    public Car[] getAll() {
        return carArrayRepository.getAll();
    }

    public void print(String id) {
        checkId(id);
        System.out.println(carArrayRepository.getById(id));
    }

    public void printAll() {
        Car[] cars = getAll();
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    public void check(Car ... cars) {
        for (Car car : cars) {
            check(car);
        }
    }

    public void changeColor(String id, Color color) {
        checkId(id);
        carArrayRepository.updateColor(id, color);
    }

    public void changeColor(String id) {
        changeColor(id, RandomGenerator.getRandomColor());
    }

    private void check(Car car) {
        checkId(car.getId());
        if (car.getCount() < 1) {
            System.out.println("This car is not available");
        }
        if (car.getEngine().getPower() < 200) {
            System.out.println("Car power less than 200 hp");
        } else if (car.getCount() > 0) {
            System.out.println("Car ready to sell");
        }
    }

    private void checkId(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id shouldn`t be empty");
        }
    }
}
