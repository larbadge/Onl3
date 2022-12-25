package com.mikhalov.model;

import lombok.Getter;
import lombok.Setter;

public class Truck extends Car {

    @Getter
    @Setter
    private int loadCapacity = 0;

    public Truck() {

    }

    public Truck(String manufacturer, Engine engine, Color color) {
        super(manufacturer, engine, color, CarType.TRUCK);
    }

    public Truck (String id, String manufacturer, Engine engine, Color color, int count, int price, int loadCapacity) {
        super(id, manufacturer, engine, color, CarType.TRUCK, count, price);
        this.loadCapacity = loadCapacity;


    }

    @Override
    public void restore() {
        this.setCount(50);
    }

    @Override
    public String  toString() {
        return "Truck " + super.toString();

    }
}
