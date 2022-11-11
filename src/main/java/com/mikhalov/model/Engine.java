package com.mikhalov.model;

public class Engine {

    private EngineType type;
    private int power;

    public Engine(EngineType type, int power) {
        this.type = type;
        this.power = power;
    }

    public EngineType getType() {
        return type;
    }

    public void setType(EngineType type) {
        this.type = type;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return String.format("type - %s, power %d", type.toString(), power);
    }

    public enum EngineType {
        E_CAR,
        PETROL,
        DIESEL,
        GAS,
        HYBRID;
    }
}
