package com.mikhalov;

import com.mikhalov.repository.CarArrayRepository;
import com.mikhalov.service.CarService;

public class Main {

    public static void main(String[] args) {
        CarService carService = new CarService(new CarArrayRepository());
        int count = carService.create();
        System.out.println(count);

        System.out.println();
        carService.check(carService.getAll());

    }
}


