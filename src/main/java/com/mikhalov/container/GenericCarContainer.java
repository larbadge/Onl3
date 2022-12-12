package com.mikhalov.container;

import com.mikhalov.model.Car;
import com.mikhalov.util.RandomGenerator;

public class GenericCarContainer<T extends Car> {

    private final T car;

    public GenericCarContainer(T car) {
        this.car = car;
    }

    public void print() {
        System.out.println(car);
    }

    public void increaseCount() {
        int count = car.getCount() + RandomGenerator.generateRandomNumForIncreaseCount();
        car.setCount(count);
    }

    public <V extends Number> void increaseCount(V number) {
        int count = car.getCount() + number.intValue();
        car.setCount(count);
    }

}
