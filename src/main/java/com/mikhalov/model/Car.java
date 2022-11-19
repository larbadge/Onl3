package com.mikhalov.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.UUID;

@Getter
@Setter
public class Car {

    final static Random random = new Random();

    private String manufacturer;
    private Engine engine;
    private Color color;
    private int count;
    private int price;
    private final String id;

    public Car() {
        this.id = UUID.randomUUID().toString();
    }

    public Car(String manufacturer, Engine engine, Color color) {
        this.manufacturer = manufacturer;
        this.engine = engine;
        this.color = color;
        this.count = 1;
        this.price = (int) (Math.random() * 10001 + 10000);
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return String.format("{Manufacturer: %s; Engine: %s; Color: %s; Count %d; Price %d$}"
                , getManufacturer(), getEngine(), getColor(), getCount(), getPrice());
    }
}
