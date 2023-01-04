package com.mikhalov;

import com.mikhalov.container.CarComparator;
import com.mikhalov.container.CarTree;
import com.mikhalov.container.GenericCarContainer;
import com.mikhalov.model.Car;
import com.mikhalov.model.Engine;
import com.mikhalov.repository.CarArrayRepository;
import com.mikhalov.service.CarService;
import com.mikhalov.util.RandomGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        CarService carService = new CarService(new CarArrayRepository());
        carService.create();
        CarTree carTree = new CarTree(new CarComparator());
        for (var car : carService.getAll()) {
            new GenericCarContainer<>(car).increaseCount();
            carTree.add(car);
        }
        System.out.println(carTree.summaryCount());
        carTree.traverseInOrder();
        System.out.println();
        List<Car> cars = Arrays.asList(carService.getAll());
        Map<String, Integer> stringCarMap = carService.toMapManufactCount(cars);
        Map<Integer, Car> integerCarMap = carService.toMapEnginePowerCar(cars);
        Map<Engine.EngineType, List<Car>> engineListMap = carService.toMapListOfCarsSameEnginType(cars);
        System.out.println(stringCarMap);
        System.out.println();
        System.out.println(integerCarMap);
        System.out.println();
        for (var set : engineListMap.entrySet()) {
            System.out.println(set);
        }

    }


}


