package com.mikhalov;

import com.mikhalov.model.Car;
import com.mikhalov.repository.CarArrayRepository;
import com.mikhalov.service.CarService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        CarService carService = new CarService(new CarArrayRepository());
        carService.create();

        List<Car> cars = Arrays.asList(carService.getAll());

        carService.findManufacturerByPrice(cars, 15000);
        System.out.println(carService.countSum(cars));
        System.out.println(carService.mapToMap(cars));
        System.out.println(carService.statistic(cars));
        System.out.println(carService.priceCheck(cars, 12000));
        CarService carService1 = new CarService(new CarArrayRepository());
        carService.create();
        List<Car> cars1 = Arrays.asList(carService1.getAll());
        List<List<Car>> carsLists = List.of(cars, cars1);
        System.out.println(carService.innerList(carsLists, 10000));

        Car newCar = carService.createNewCar(Car.CarType.CAR);
        Car car = carService.mapToObject(carService.carToMap(newCar));
        System.out.println(newCar);
        System.out.println(car);
        System.out.println(car.equals(newCar));

        List<Map<String, Object>> maps = carService.toListOfMap(cars);
        List<Car> cars2 = carService.toListOfCars(maps);
        System.out.println(cars2);

    }
}

