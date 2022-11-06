package com.mikhalov;

import com.mikhalov.model.Car;
import com.mikhalov.service.CarService;

public class Main {

    public static void main(String[] args) {
        Car firstCar = CarService.create();
        Car secondCar = CarService.create();
        Car thirdCar = CarService.create();
        CarService.print(firstCar, secondCar, thirdCar);
    }


}
