package com.mikhalov.model;

public class Car {

    private String manufacturer;
    private String engine;
    private String color;
    private int count;
    private int price;

    public Car() {
        this.count = 1;
        this.price = (int) (Math.random() * 10001 + 10000);
    }

    public Car(String manufacturer, String engine, String color) {
        this();
        this.manufacturer = manufacturer;
        this.engine = engine;
        this.color = color;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
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

    @Override
    public String toString(){
        return String.format("{Manufacturer: %s; Engine: %s; Color: %s; Count %d} price %d$"
                , getManufacturer(), getEngine(),getColor(),getCount(), getPrice());
    }
}
