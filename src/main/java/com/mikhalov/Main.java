package com.mikhalov;

import com.mikhalov.repository.CarArrayRepository;
import com.mikhalov.service.CarService;

public class Main {

    public static void main(String[] args) {
        CarService carService = new CarService(new CarArrayRepository());
        carService.create(3);
        carService.printAll();

        System.out.println();
        carService.insert(carService.createNewCar(), 0);
        carService.printAll();

        System.out.println();
        carService.check(carService.getAll());
    }
}


