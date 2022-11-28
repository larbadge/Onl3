package com.mikhalov.model;

public class Truck extends Car {

    private int loadCapacity;

    public Truck() {

    }

    public Truck(String manufacturer, Engine engine, Color color) {
        super(manufacturer, engine, color);
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
