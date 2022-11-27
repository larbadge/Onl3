package com.mikhalov.service;

import com.mikhalov.model.Car;
import com.mikhalov.model.PassengerCar;
import com.mikhalov.repository.CarArrayRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {
    CarService target;
    CarArrayRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(CarArrayRepository.class);
        target = new CarService(repository);
    }

    @Test
    void createNewCarTest() {
        Assertions.assertNotNull(target.createNewCar(Car.CarType.CAR));
    }

    @Test
    void createTest() {
        Mockito.doReturn(new Car[0]).when(repository).getAll();
        int expected = target.create();
        Assertions.assertTrue(expected == -1 || (expected > 0 && expected <= 10));
    }

    @Test
    void insertNegativeIndexExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> target.insert(new PassengerCar(), -1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @NullSource
    void checkIdTest(String id) {
        try {
            Method method = CarService.class.getDeclaredMethod("checkId", String.class);
            method.setAccessible(true);
            var exception = assertThrows(IllegalArgumentException.class, () -> {
                try {
                    method.invoke(target, id);
                } catch (InvocationTargetException e) {
                    throw e.getCause();
                }
            });
            assertEquals("ID shouldn`t be empty", exception.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({"1, 200, 'Car ready to sell\r\n'",
            "0, 200, 'This car is not available\r\n'",
            "1, 50, 'Car power less than 200 hp\r\n'",
            """
                    0, 50, 'This car is not available\r
                    Car power less than 200 hp\r
                    '"""
    })
    void checkTest(int count, int power, String expectedResult) {
        String consoleOutput = null;
        PrintStream originalOut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(100);
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            Car expected = target.createNewCar(Car.CarType.TRUCK);
            expected.setCount(count);
            expected.getEngine().setPower(power);
            target.check(expected);
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(expectedResult, consoleOutput);
    }

}