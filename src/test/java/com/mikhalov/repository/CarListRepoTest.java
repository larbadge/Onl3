package com.mikhalov.repository;

import com.mikhalov.model.Car;
import com.mikhalov.model.Color;
import com.mikhalov.model.PassengerCar;
import com.mikhalov.util.RandomGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarListRepoTest {
    CarRepository repository;
    Car car;
    Car car1;
    Car car2;
    List<Car> cars;

    @BeforeEach
    void setUp() {
        repository = new CarListRepo();
        car = new PassengerCar(RandomGenerator.getRandomString(),
                RandomGenerator.getRandomEngine(), RandomGenerator.getRandomColor());
        car1 = new PassengerCar(RandomGenerator.getRandomString(),
                RandomGenerator.getRandomEngine(), RandomGenerator.getRandomColor());
        car2 = new PassengerCar(RandomGenerator.getRandomString(),
                RandomGenerator.getRandomEngine(), RandomGenerator.getRandomColor());
        repository.save(car);
        repository.save(car1);
        repository.save(car2);
        cars = repository.getAll();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void getCarIndex() {
        int i = 0;
        for (var c : cars) {
            int actual = repository.getCarIndex(c);
            assertEquals(i, actual);
            i++;
        }
    }

    @Test
    void save() {
        assertEquals(3, cars.size());
        assertEquals(cars.get(0), car);
        assertEquals(cars.get(1), car1);
        assertEquals(cars.get(2), car2);
    }

    @Test
    void insert() {
        repository.insert(car2, 1);
        cars = repository.getAll();
        assertEquals(4, cars.size());
        assertEquals(car, cars.get(0));
        assertEquals(car2, cars.get(1));
        assertEquals(car1, cars.get(2));
        assertEquals(car2, cars.get(3));

    }

    @Test
    void getById() {
        Car actual = repository.getById(car.getId()).get();
        assertEquals(car, actual);

    }

    @Test
    void getAll() {
        List<Car> expected = List.of(car, car1, car2);
        assertEquals(expected, cars);
    }

    @Test
    void delete() {
        repository.delete(car1.getId());
        cars = repository.getAll();
        List<Car> expected = List.of(car, car2);
        assertEquals(expected, cars);
    }

    @Test
    void deleteAll() {
        repository.deleteAll();
        List<Car> expected = Collections.emptyList();
        cars = repository.getAll();
        assertEquals(expected, cars);
    }

    @Test
    void updateColor() {
        for (Car c : cars) {
            Color color = c.getColor();
            if (color != Color.BLACK) {
                c.setColor(Color.BLACK);
                assertEquals(Color.BLACK, c.getColor());
            } else {
                c.setColor(Color.RED);
                assertEquals(Color.RED, c.getColor());
            }
        }
    }

    @Test
    void sortById() {
        cars = repository.getAll().stream().sorted((Comparator.comparing(Car::getId))).toList();
        repository.sortById();
        List<Car> actual = repository.getAll();
        assertEquals(cars, actual);
        Car car3 = new PassengerCar(RandomGenerator.getRandomString(),
                RandomGenerator.getRandomEngine(), RandomGenerator.getRandomColor());
        Car car4 = new PassengerCar(RandomGenerator.getRandomString(),
                RandomGenerator.getRandomEngine(), RandomGenerator.getRandomColor());
        repository.save(car3);
        repository.save(car4);
        List<Car> expected1 = new ArrayList<>(cars);
        expected1.add(car3);
        expected1.add(car4);
        List<Car> actual1 = repository.getAll();
        assertEquals(expected1, actual1);

    }
}