package com.mikhalov.model;

import lombok.Getter;
import lombok.Setter;

public class PassengerCar extends Car {

    @Getter
    @Setter
    private int passengerCount = 0;

    public PassengerCar() {

    }

    public PassengerCar(String manufacturer, Engine engine, Color color) {
        super(manufacturer, engine, color, CarType.CAR);
    }

    public PassengerCar (String id, String manufacturer, Engine engine, Color color, int count, int price, int passengerCount) {
        super(id, manufacturer, engine, color, CarType.CAR, count, price);
        this.passengerCount = passengerCount;
    }

    @Override
    public void restore() {
        this.setCount(100);
    }

    @Override
    public String toString() {
        return "Passenger car " + super.toString();
    }
}
