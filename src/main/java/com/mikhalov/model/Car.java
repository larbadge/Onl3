package com.mikhalov.model;

import java.util.Random;
import java.util.UUID;

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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("{Manufacturer: %s; Engine: %s; Color: %s; Count %d; Price %d$}"
                , getManufacturer(), getEngine(), getColor(), getCount(), getPrice());
    }
}
