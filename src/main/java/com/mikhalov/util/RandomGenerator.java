package com.mikhalov.util;

import com.mikhalov.model.Car;
import com.mikhalov.model.Color;
import com.mikhalov.model.Engine;

import java.util.Random;

public class RandomGenerator {

    private static final Random random = new Random();

    public static int generateRandomNum() {
        return random.nextInt(0, 11);
    }

    public static String getRandomString() {
        StringBuilder sb = new StringBuilder();
        int length = random.nextInt(4, 10);
        String str = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < length; i++) {
            sb.append(str.charAt(random.nextInt(str.length())));
        }
        return sb.toString();
    }

    public static Engine getRandomEngine() {
        Engine.EngineType[] types = Engine.EngineType.values();
        return new Engine(types[random.nextInt(types.length)], random.nextInt(1001));
    }

    public static Color getRandomColor() {
        Color[] colors = Color.values();
        return colors[random.nextInt(colors.length)];
    }

    public static Car.CarType getRandomCarType() {
        Car.CarType[] types = Car.CarType.values();
        return types[random.nextInt(types.length)];
    }
}
