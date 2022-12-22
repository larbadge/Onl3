package com.mikhalov.service;

import com.mikhalov.model.*;
import com.mikhalov.repository.CarRepository;
import com.mikhalov.util.RandomGenerator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarService {

    private final CarRepository carRepository;

    public CarService(final CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Map<String, Integer> toMapManufactCount(List<Car> cars) {
        return cars.stream()
                .collect(Collectors.toMap(Car::getManufacturer, Car::getCount));
    }

    public Map<Integer, Car> toMapEnginePowerCar(List<Car> cars) {
        return cars.stream()
                .collect(Collectors.toMap(car -> car.getEngine().getPower(), car -> car));
    }

    public Map<Engine.EngineType, List<Car>> toMapListOfCarsSameEnginType(List<Car> cars) {
        return cars.stream()
                .collect(Collectors.toMap(car -> car.getEngine().getType(), List::of,
                        (a, b) -> Stream.concat(a.stream(), b.stream())
                        .collect(Collectors.toList())));
    }

    public void printManufacturerAndCount(Car car) {
        Optional.ofNullable(car)
                .ifPresent(c -> System.out.printf("Manufacturer: %s, count = %d%n", c.getManufacturer(), c.getCount()));
    }

    public void printColor(Car car) {
        Optional.ofNullable(car)
                .map(Car::getColor)
                .ifPresentOrElse(System.out::println, this::printRandomCarColor);
    }

    public void checkCount(Car car) {
        Car forCheck = Optional.ofNullable(car)
                .filter(car1 -> car1.getCount() > 10)
                .orElseThrow(UserInputException::new);
        printManufacturerAndCount(forCheck);
    }

    public void checkCount(Car[] cars) {
        Arrays.stream(cars).forEach(this::checkCount);
    }

    public void printEngineInfo(Car car) {
        Optional.ofNullable(car)
                .or(() -> {
                    System.out.println("No car, new random car will be created");
                    return Optional.of(createNewRandomCar());
                })
                .map(c -> c.getEngine().getPower())
                .ifPresent(power -> System.out.println("Car's engine power = " + power));
    }

    public void printInfo(Car car) {
        Optional.ofNullable(car)
                .map(Car::getId)
                .ifPresentOrElse(this::print, () -> print(createNewRandomCar().getId()));
    }

    public boolean carEquals(Car c1, Car c2) {
        if (c1.getClass().equals(c2.getClass()) && c1.hashCode() == c2.hashCode()) {
            return c1.equals(c2);
        } else {
            return false;
        }
    }

    public void create(int count, Car.CarType carType) {
        for (; count > 0; count--) {
            createNewCar(carType);
        }
    }

    public int create() {
        int count = RandomGenerator.generateRandomPositiveNumBoundTen();
        if (count == 0) {
            return -1;
        }
        create(count);
       // printAll();
        return count;
    }

    private void create(int count) {
        for (; count > 0; count--) {
            createNewRandomCar();
        }
    }

    private Car createNewRandomCar() {
        return createNewCar(RandomGenerator.getRandomCarType());
    }

    public Car createNewCar(Car.CarType carType) {
        return switch (carType) {
            case CAR -> createPassengerCar();
            case TRUCK -> createTruck();
            default -> {
                throw new IllegalArgumentException();
            }
        };
    }

    private Car createPassengerCar() {
        Car car = new PassengerCar(RandomGenerator.getRandomString(),
                RandomGenerator.getRandomEngine(), RandomGenerator.getRandomColor());
        carRepository.save(car);
        return car;
    }

    private Car createTruck() {
        Car car = new Truck(RandomGenerator.getRandomString(),
                RandomGenerator.getRandomEngine(), RandomGenerator.getRandomColor());
        carRepository.save(car);
        return car;
    }

    public void insert(Car car, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index less than 0");
        }
        if (car != null) {
            carRepository.insert(car, index);
        }
    }

    public Car find(String id) {
        checkId(id);
        return carRepository.getById(id);
    }

    public void delete(String id) {
        checkId(id);
        carRepository.delete(id);
    }

    public Car[] getAll() {
        return carRepository.getAll();
    }

    public void print(String id) {
        checkId(id);
        System.out.println(carRepository.getById(id));
    }

    public void printAll() {
        for (Car car : getAll()) {
            System.out.println(car);
        }
    }

    public void check(Car... cars) {
        for (Car car : cars) {
            check(car);
        }
    }

    public void changeColor(String id, Color color) {
        checkId(id);
        carRepository.updateColor(id, color);
    }

    public void changeColor(String id) {
        changeColor(id, RandomGenerator.getRandomColor());
    }

    public void sortById() {
        carRepository.sortById();
    }

    public int getCarIndex(Car car) {
        return carRepository.getCarIndex(car);
    }

    private void check(Car car) {
        if (car.getEngine() == null) {
            return;
        }
        checkId(car.getId());
        if (car.getCount() < 1) {
            System.out.println("This car is not available");
        }
        if (car.getEngine().getPower() < 200) {
            System.out.println("Car power less than 200 hp");
        } else if (car.getCount() > 0) {
            System.out.println("Car ready to sell");
        }
    }

    private void checkId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID shouldn`t be empty");
        }
    }

    private void printRandomCarColor() {
        System.out.println(createNewRandomCar().getColor());
    }
}
