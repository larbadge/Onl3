package com.mikhalov.homework5;

import java.util.Arrays;
import java.util.Random;

public class HomeWork5 {

    public static int IndexOfLastMaxNum() {
        int[] arr = createIntRandArr(12, -15, 16);
        int indexOfMaxElement = -1;
        for (int i = 0, maxElement = Integer.MIN_VALUE; i < arr.length; i++) {
            if (arr[i] >= maxElement) {
                maxElement = arr[i];
                indexOfMaxElement = i;
            }
        }
        return indexOfMaxElement;
    }

    public static void replaceAllOddIndexesToZero() {
        int[] arr = createIntRandArr(8, 1, 11);
        System.out.println(Arrays.toString(arr));
        for (int i = 1; i < arr.length; i += 2) {
            arr[i] = 0;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static boolean isRandomArrSorted() {
        int[] arr = createIntRandArr(4, 10, 100);
        int[] copyArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copyArr);
        return Arrays.equals(arr, copyArr);
    }

    public static void printArrWithBiggerAverage() {
        int[] firstArr = createIntRandArr(5, 0, 6);
        int[] secondArr = createIntRandArr(5, 0, 6);
        System.out.printf("first array: %s%n" + "second array: %s%n",
                Arrays.toString(firstArr), Arrays.toString(secondArr));
        double firstAverage = Arrays.stream(firstArr).average().getAsDouble();//ok or not?
        double secondAverage = Arrays.stream(secondArr).average().getAsDouble();//?
        if (firstAverage > secondAverage) {
            System.out.printf("first array have bigger average = %.2f", firstAverage);
        } else if (firstAverage < secondAverage) {
            System.out.printf("second array have bigger average = %.2f", secondAverage);
        } else {
            System.out.printf("arrays have equal average = %.2f", secondAverage);
        }
    }

    private static int[] createIntRandArr(int length, int rangeFrom, int to) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length should be more then 0");
        }
        if (rangeFrom - to == 0) {
            throw new IllegalArgumentException("Range should be more then 0");
        }
        Random random = new Random();
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(rangeFrom, to);
        }
        return arr;
    }
}
