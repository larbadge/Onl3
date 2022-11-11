package com.mikhalov.service;

import com.mikhalov.model.Car;
import com.mikhalov.model.Color;
import com.mikhalov.model.Engine;
import com.mikhalov.repository.CarArrayRepository;

import java.util.Random;

public class CarService {

    private final CarArrayRepository carArrayRepository;

    final Random random = new Random();

    public CarService(final CarArrayRepository carArrayRepository) {
        this.carArrayRepository = carArrayRepository;
    }

    public void create(int count) {
        for (; count > 0; count--) {
            create();
        }
    }

    public void create() {
        carArrayRepository.save(createNewCar());
    }

    public Car createNewCar() {
        return new Car(getRandomString(), getRandomEngine(), getRandomColor());
    }

    public void insert(Car car, int index) {
        checkId(car.getId());
        if (index < 0) {
            throw new IllegalArgumentException("index less than 0");
        }
        carArrayRepository.insert(car, index);
    }

    public Car find(String id) {
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

    public void printById(String id) {
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
        changeColor(id, getRandomColor());
    }

    private Color getRandomColor() {
        Color[] colors = Color.values();
        return colors[random.nextInt(colors.length)];
    }

    private Engine getRandomEngine() {
        Engine.EngineType[] types = Engine.EngineType.values();
        return new Engine(types[random.nextInt(types.length)], random.nextInt(1001));
    }

    private String getRandomString() {
        StringBuilder sb = new StringBuilder();
        int length = random.nextInt(4, 10);
        String str = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < length; i++) {
            sb.append(str.charAt(random.nextInt(str.length())));
        }
        return sb.toString();
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
