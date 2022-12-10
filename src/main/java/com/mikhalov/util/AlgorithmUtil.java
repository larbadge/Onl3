package com.mikhalov.util;

import com.mikhalov.model.Car;
import com.mikhalov.repository.CarArrayRepository;
import com.mikhalov.service.CarService;

import java.util.Arrays;
import java.util.Comparator;

public class AlgorithmUtil {



    public static void main(String[] args) {
        CarService carService = new CarService(new CarArrayRepository());
        carService.create(5, Car.CarType.TRUCK);
        Car[] cars = carService.getAll();
        for (var car :
                cars) {
            System.out.print(car.getId() + "; ");
        }
        System.out.println("\n");
        mergeSort(cars, Comparator.comparing(Car::getId));
        for (var car :
                cars) {
            System.out.print(car.getId() + "; ");
        }

    }

    public static <T> void mergeSort(T[] array, Comparator<T> comp) {

        T[] arrayCopy = Arrays.copyOf(array, array.length);
        int startIndex = 0;
        int finishIndex = array.length - 1;
        mergeSort(array, arrayCopy, comp, startIndex, finishIndex);
    }

    private static <T> void mergeSort(T[] array, T[] arrayCopy, Comparator<T> comp, int startIndex, int finishIndex) {
        if (startIndex >= finishIndex) {
            return;
        }
        int mid = (startIndex + finishIndex) >>> 1;
        mergeSort(array, arrayCopy, comp, startIndex, mid);
        mergeSort(array, arrayCopy, comp, mid + 1, finishIndex);
        merge(array, arrayCopy, comp, startIndex, mid, mid + 1, finishIndex);
    }

    private static <T> void merge(T[] array, T[] arrayCopy, Comparator<T> comp, int lc, int le, int rc, int re) {
        if (re + 1 - lc >= 0) {
            System.arraycopy(array, lc, arrayCopy, lc, re + 1 - lc);
        }
        for (int i = lc; i <= re; i++) {
            if (lc > le) {
                array[i] = arrayCopy[rc];
                rc++;
            } else if (rc > re) {
                array[i] = arrayCopy[lc];
                lc++;
            } else if (comp.compare(arrayCopy[lc], arrayCopy[rc]) < 0) {
                array[i] = arrayCopy[lc];
                lc++;
            } else {
                array[i] = arrayCopy[rc];
                rc++;
            }
        }
    }

    public static int interpolationSearch(Integer[] ints, int wanted) {
        int lowEnd = 0;
        int highEnd = ints.length - 1;
        while (wanted >= ints[lowEnd] && wanted <= ints[highEnd]) {
            int probe = lowEnd +
                    (((highEnd - lowEnd) * (wanted - ints[lowEnd])) / (ints[highEnd] - ints[lowEnd]));
            if (lowEnd == highEnd) {
                if (ints[lowEnd] == wanted) {
                    return lowEnd;
                } else return -1;
            }
            if (ints[probe] == wanted) {
                return probe;
            }
            if (ints[probe] < wanted) {
                lowEnd = probe + 1;
            } else {
                highEnd = probe - 1;
            }
        }
        return -1;
    }

    public static <T> int binarySearch(T[] array, T wanted, Comparator<T> comp) {
        int l = 0;
        int r = array.length - 1;

        while (l <= r) {
            int mid = (l + r) >>> 1;
            T midVal = array[mid];
            int cmp = comp.compare(midVal, wanted);
            if (cmp < 0)
                l = mid + 1;
            else if (cmp > 0)
                r = mid - 1;
            else
                return mid;
        }
        return -1;
    }

}

