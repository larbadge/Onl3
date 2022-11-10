package com.mikhalov.service;

import com.mikhalov.model.Car;

import java.util.Random;

public class CarService {

    public Car create() {
        return new Car(getRandomString(), getRandomString(), getRandomString());
    }

    public void print(Car... cars) {
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    private static String getRandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int length = random.nextInt(4, 10);
        String str = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < length; i++) {
            sb.append(str.charAt(random.nextInt(str.length())));
        }
        return sb.toString();
    }

}
