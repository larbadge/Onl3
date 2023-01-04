package com.mikhalov.container;

import com.mikhalov.model.Car;
import com.mikhalov.repository.CarArrayRepository;
import com.mikhalov.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CarTreeTest {
    CarService carService = new CarService(new CarArrayRepository());
    CarTree cars;
    Car newCar = carService.createNewCar(Car.CarType.CAR);
    Car newCar1 = carService.createNewCar(Car.CarType.CAR);
    Car newCar2 = carService.createNewCar(Car.CarType.CAR);
    Car newCar3 = carService.createNewCar(Car.CarType.CAR);
    Car newCar4 = carService.createNewCar(Car.CarType.CAR);


    @BeforeEach
    void setUp() {
        cars = new CarTree();
        cars.add(newCar);
        cars.add(newCar1);
        cars.add(newCar2);
        cars.add(newCar3);
        cars.add(newCar4);
    }

    @Test
    void isAddedCarsTest() {
        assertTrue(cars.isContainsValue(newCar));
        assertTrue(cars.isContainsValue(newCar1));
        assertTrue(cars.isContainsValue(newCar2));
        assertTrue(cars.isContainsValue(newCar3));
        assertTrue(cars.isContainsValue(newCar4));
    }

    @Test
    void deleteCarTest() {
        assertDoesNotThrow(() -> cars.delete(newCar));
        assertTrue(cars.isContainsValue(newCar4)
                && cars.isContainsValue(newCar3)
                && cars.isContainsValue(newCar2)
                && cars.isContainsValue(newCar1)
                && !cars.isContainsValue(newCar));
    }

    @Test
    void summaryCountTest() {
        int expected = 0;
        for (var car : carService.getAll()) {
            expected += car.getCount();
        }
        int actual = cars.summaryCount();
        assertEquals(expected, actual);
    }

    @Test
    void traverseInOrderTest() {
        ArrayList<Car> carsList = new ArrayList<>();
        carsList.add(newCar);
        carsList.add(newCar1);
        carsList.add(newCar2);
        carsList.add(newCar3);
        carsList.add(newCar4);
        carsList.sort(Comparator.comparingInt(Objects::hashCode));

        String consoleOutput = null;
        StringBuilder expected = new StringBuilder();
        PrintStream originalOut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(100);
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            for (Car car : carsList) {
                expected.append(car.toString()).append(System.lineSeparator());
            }
            cars.traverseInOrder();
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOut);
        } catch (Exception e) {
        e.printStackTrace();
    }
    assertEquals(expected.toString(), consoleOutput);
    }
}