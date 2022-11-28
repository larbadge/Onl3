package com.mikhalov;

import com.mikhalov.model.Car;
import com.mikhalov.service.CarService;
import com.mikhalov.repository.CarArrayRepository;

public class Main {

    public static void main(String[] args) {
        CarService carService = new CarService(new CarArrayRepository());
        carService.createTruck();
        carService.createPassengerCar();
        int count = carService.create();
        System.out.println(count);

        System.out.println();
        for (Car car : carService.getAll()) {
            car.restore();
        }
        carService.printAll();

    }
}


