package com.mikhalov.model;

public class PassengerCar extends Car implements CountRestore{

    private int passengerCount;

    public PassengerCar() {

    }

    public PassengerCar(String manufacturer, Engine engine, Color color) {
        super(manufacturer, engine, color);
    }

    @Override
    public void restore() {
        this.setCount(100);
    }

    @Override
    public String toString() {
        return "Passenger Car-" + super.toString();
    }
}
