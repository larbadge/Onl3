package com.mikhalov.service;

import com.mikhalov.model.*;
import com.mikhalov.repository.CarRepository;
import com.mikhalov.util.RandomGenerator;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarService {

    private final CarRepository carRepository;
    private final Function<Map<String, Object>, Car> mapper = this::createCarByMap;

    public CarService(final CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public Map<String, Object> carToMap(Car car) {
        Map<String, Object> carFields = new HashMap<>();
        carFields.put("id", car.getId());
        carFields.put("type", car.getCarType());
        carFields.put("manufacturer", car.getManufacturer());
        carFields.put("engine", car.getEngine());
        carFields.put("color", car.getColor());
        carFields.put("count", car.getCount());
        carFields.put("price", car.getPrice());
        int passengerCount = 0;
        int loadCapacity = 0;
        if (car.getClass() == PassengerCar.class) {
            PassengerCar car1 = (PassengerCar) car;
            passengerCount = car1.getPassengerCount();
        }
        if (car.getClass() == Truck.class) {
            Truck car1 = (Truck) car;
            loadCapacity = car1.getLoadCapacity();
        }
        carFields.put("passengerCount",passengerCount);
        carFields.put("loadCapacity", loadCapacity);

        return carFields;
    }

    public List<Map<String, Object>> toListOfMap(List<Car> cars) {
        return cars.stream()
                .map(this::carToMap)
                .collect(Collectors.toList());
    }

    public Car mapToObject(final Map<String, Object> map) {
        return mapper.apply(map);

    }

    private Car createCarByMap(final Map<String, Object> map) {
        Car.CarType carType = (Car.CarType) map.getOrDefault("type", Car.CarType.CAR);
        String id = (String) map.getOrDefault("id", "null");
        String manufacturer = (String) map.getOrDefault("manufacturer", "null");
        Engine engine = (Engine) map.getOrDefault("engine", new Engine());
        Color color = (Color) map.getOrDefault("color", null);
        int count = (int) map.getOrDefault("count", 0);
        int price = (int) map.getOrDefault("price", 0);
        int passengerCount = (int) map.getOrDefault("passengerCount", 0);
        int loadCapacity = (int) map.getOrDefault("loadCapacity", 0);

        if (carType.equals(Car.CarType.CAR)) {
            return new PassengerCar(id, manufacturer, engine, color, count, price, passengerCount);
        } else {
            return new Truck(id, manufacturer, engine, color, count, price, loadCapacity);
        }
    }

    public List<Car> toListOfCars(List<Map<String, Object>> maps) {
        return maps.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    public void findManufacturerByPrice(List<Car> cars, int price) {
        cars.stream()
                .filter(car -> car.getPrice() > price)
                .map(Car::getManufacturer)
                .forEach(System.out::println);
    }

    public int countSum(List<Car> cars) {
        return cars.stream()
                .map(Car::getCount)
                .reduce(0, Integer::sum);
    }

    public Map<String, Car.CarType> mapToMap(List<Car> cars) {
        return cars.stream()
                .sorted(Comparator.comparing(Car::getManufacturer))
                .distinct()
                .collect(Collectors.toMap(Car::getId, Car::getCarType, (a, b) -> b,  LinkedHashMap::new));
    }

    public String statistic(List<Car> cars) {
        return cars.stream()
                .mapToInt(Car::getPrice)
                .summaryStatistics()
                .toString();
    }

    public boolean priceCheck(List<Car> cars, int price) {
        return cars.stream()
                .mapToInt(Car::getPrice)
                .allMatch(val -> val > price);
    }

    public Map<Color, Integer> innerList(List<List<Car>> cars, int price) {
        return cars.stream()
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Car::getColor))
                .distinct()
                .peek(System.out::println)
                .filter(val -> val.getPrice() > price)
                .collect(Collectors.toMap(Car::getColor, Car::getCount, Integer::sum));
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
                .map(Car::getEngine)
                .map(Engine::getPower)
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
