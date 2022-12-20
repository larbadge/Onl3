package com.mikhalov;

import com.mikhalov.container.GenericCarContainer;
import com.mikhalov.model.Car;
import com.mikhalov.repository.CarArrayRepository;
import com.mikhalov.service.CarService;

public class Main {

    public static void main(String[] args) {
        CarService carService = new CarService(new CarArrayRepository());

        GenericCarContainer<Car> genericCarContainer = new GenericCarContainer<>(carService.createNewCar(Car.CarType.CAR));
        genericCarContainer.increaseCount(3.6);
        genericCarContainer.print();
        genericCarContainer.increaseCount();
        genericCarContainer.print();
    }
}


