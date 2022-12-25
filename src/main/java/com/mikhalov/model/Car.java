package com.mikhalov.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public abstract class Car implements CountRestore {

    @Setter(AccessLevel.NONE)
    private String manufacturer;
    private Engine engine;
    private Color color;
    private int count;
    private int price;
    private final String id;
    private CarType carType;

    public Car() {
        this.id = UUID.randomUUID().toString();
    }

    public Car(String manufacturer, Engine engine, Color color, CarType carType) {
        this.manufacturer = manufacturer;
        this.engine = engine;
        this.color = color;
        this.carType = carType;
        this.count = 1;
        this.price = (int) (Math.random() * 10001 + 10000);
        this.id = UUID.randomUUID().toString();
    }

    protected Car (String id, String manufacturer, Engine engine, Color color, CarType carType, int count, int price) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.engine = engine;
        this.color = color;
        this.carType = carType;
        this.count = count;
        this.price = price;
    }

    public enum CarType {
        CAR,
        TRUCK
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(manufacturer, car.manufacturer) && Objects.equals(engine, car.engine) && color == car.color && Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, engine, color, id);
    }

    @Override
    public String toString() {
        return String.format("{Manufacturer: %s; Engine: %s; Color: %s; Count %d; Price %d$}"
                , getManufacturer(), getEngine(), getColor(), getCount(), getPrice());
    }
}
