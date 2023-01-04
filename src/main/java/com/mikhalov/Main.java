package com.mikhalov;

import com.mikhalov.model.Car;
import com.mikhalov.model.PassengerCar;
import com.mikhalov.repository.CarArrayRepository;
import com.mikhalov.service.CarService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        CarService carService = new CarService(new CarArrayRepository());
        List<Car> cars = carService.carsFromFile("carlist.xml");
        cars.forEach(System.out::println);

        System.out.println();

        List<Car> cars1 = carService.carsFromFile("carlist.json");
        cars1.forEach(System.out::println);
        PassengerCar pc = (PassengerCar) cars1.get(1);
        System.out.println(pc.getPassengerCount());
    }
}

