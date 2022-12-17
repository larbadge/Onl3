package com.mikhalov;

import com.mikhalov.model.Car;
import com.mikhalov.repository.CarArrayRepository;
import com.mikhalov.service.CarService;
import com.mikhalov.util.AlgorithmUtil;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        CarService carService = new CarService(new CarArrayRepository());

        carService.create(5, Car.CarType.TRUCK);
        for (var car :
                carService.getAll()) {
            System.out.print(car.getId() + "; ");
        }
        System.out.println("\n");
        carService.sortById();
        for (var car :
                carService.getAll()) {
            System.out.print(car.getId() + "; ");

        }
        System.out.println();
        System.out.println(carService.searchById(carService.getAll()[3].getId()));
    }
}


