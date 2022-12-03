package com.mikhalov.repository;

import com.mikhalov.model.Car;
import com.mikhalov.model.Color;
import com.mikhalov.model.PassengerCar;
import com.mikhalov.model.Truck;
import com.mikhalov.util.RandomGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CarArrayRepositoryTest {
    CarArrayRepository repository;
    Car car;


    @BeforeEach
    void setUp() {
        repository = new CarArrayRepository();
        car = new PassengerCar(RandomGenerator.getRandomString(),
                RandomGenerator.getRandomEngine(), RandomGenerator.getRandomColor());
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void saveTest() {
        repository.save(car);
        assertEquals(car, repository.getById(car.getId()));
    }

    @Test
    void saveNullTest() {
        assertThrows(NullPointerException.class, () -> repository.save(null));
    }

    @Test
    void insertTest() {
        repository.save(new PassengerCar());
        repository.save(car);
        Car carToInsert = new PassengerCar(RandomGenerator.getRandomString(),
                RandomGenerator.getRandomEngine(), RandomGenerator.getRandomColor());
        Car carToInsert2 = new Truck(RandomGenerator.getRandomString(),
                RandomGenerator.getRandomEngine(), RandomGenerator.getRandomColor());
        repository.insert(carToInsert, 1);
        repository.insert(carToInsert2, 5);
        Car[] cars = repository.getAll();
        assertEquals(car, cars[2]);
        assertEquals(carToInsert, cars[1]);
        assertEquals(carToInsert2, cars[3]);
    }

    @Test
    void deleteTest() {
        repository.save(new PassengerCar());
        repository.save(car);
        repository.save(new Truck());
        repository.save(new PassengerCar());
        repository.deleteAll();
        assertEquals(0, repository.getAll().length);
    }

    @Test
    void getByIdTest() {
        repository.save(car);
        Car actual = repository.getById(car.getId());
        assertEquals(car, actual);
    }

    @Test
    void getAllTest() {
        repository.save(new PassengerCar());
        repository.save(new Truck());
        repository.save(new PassengerCar());
        repository.save(car);
        Car[] cars = repository.getAll();
        assertEquals(4, cars.length);
        assertEquals(car, cars[3]);
    }

    @Test
    void updateColorTest() {
        repository.save(car);
        Color original = car.getColor();
        Color toUpdate;
        do toUpdate = RandomGenerator.getRandomColor();
        while (original.equals(toUpdate));
        repository.updateColor(car.getId(), toUpdate);
        assertNotEquals(original, car.getColor());
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "slf", "123", "14242421412", "", " "})
    @NullSource
    void indexByWrongIdTest(String id) {
        repository.save(new Truck());
        repository.save(car);
        try {
            Method method = CarArrayRepository.class.getDeclaredMethod("indexById", String.class);
            method.setAccessible(true);
            var exception = assertThrows(NoSuchElementException.class, () -> {
                try {
                    method.invoke(repository, id);
                } catch (InvocationTargetException e) {
                    throw e.getCause();
                }
            });
            assertEquals("Wrong ID", exception.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void indexByIdTest() {
        repository.save(new PassengerCar());
        repository.save(car);
        try {
            Method method = CarArrayRepository.class.getDeclaredMethod("indexById", String.class);
            method.setAccessible(true);
            int actual = (int) method.invoke(repository, car.getId());
            assertEquals(1, actual);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void findLengthEmptyRepoTest() {
        try {
            Method method = CarArrayRepository.class.getDeclaredMethod("findLength");
            method.setAccessible(true);
            int actual = (int) method.invoke(repository);
            assertEquals(0, actual);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void findLengthTest() {
        int count = RandomGenerator.generateRandomNum();
        for (int i = 0; i < count; i++) {
            repository.save(new PassengerCar());
        }
        try {
            Method method = CarArrayRepository.class.getDeclaredMethod("findLength");
            method.setAccessible(true);
            int actual = (int) method.invoke(repository);
            assertEquals(count, actual);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}