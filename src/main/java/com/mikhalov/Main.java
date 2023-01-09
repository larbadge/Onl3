package com.mikhalov;

import com.mikhalov.model.Car;
import com.mikhalov.repository.CarListRepo;
import com.mikhalov.repository.CarMapRepo;
import com.mikhalov.service.CarService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        CarService carService = new CarService(new CarMapRepo());
        carService.create();
        List<Car> all = carService.getAll();
        all.forEach(System.out::println);

        System.out.println();

        CarService carService1 = new CarService(new CarListRepo());
        carService1.create();
        List<Car> all1 = carService1.getAll();
        all1.forEach(System.out::println);
    }

}

