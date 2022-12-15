package com.mikhalov;

import com.mikhalov.container.CarList;
import com.mikhalov.model.Car;
import com.mikhalov.repository.CarArrayRepository;
import com.mikhalov.service.CarService;

public class Main {

    public static void main(String[] args) {
        CarService carService = new CarService(new CarArrayRepository());

        CarList<Car> list = new CarList<>();
        list.add(carService.createNewCar(Car.CarType.CAR));
        list.add(carService.createNewCar(Car.CarType.CAR));
        list.add(null);
        list.add(carService.createNewCar(Car.CarType.CAR));
        list.add(carService.createNewCar(Car.CarType.CAR));
        System.out.println(list.getSize());
        list.add(null);
        list.addFirst(null);
        list.deleteAllNullValues();
        list.printAll();

        System.out.println();
        for (Car car : list) {
            System.out.println("ITERATOR!!!");
            System.out.println(car);
        }
    }

}


