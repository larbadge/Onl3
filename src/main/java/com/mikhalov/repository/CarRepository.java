package com.mikhalov.repository;

import com.mikhalov.model.Car;
import com.mikhalov.model.Color;

public abstract class CarRepository implements Repository<Car> {

    public abstract void updateColor(String id, Color color);

    public abstract void sortById();

    public abstract int getCarIndex(Car car);


}
