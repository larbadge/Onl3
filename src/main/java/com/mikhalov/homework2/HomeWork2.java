package com.mikhalov.homework2;

public class HomeWork2 {

    public static void main(String[] args) {
        System.out.printf("Square = %.2f\n", getTriangleSquare(10, 12, 16));
        System.out.println();

        int random = minRandomNumberInAbsolute();
        String isEven = isRandomNumberEven(random) ? "even" : "odd";
        System.out.printf("Random number %d is %s\n", random, isEven);

        System.out.println(isRandomNumberEven());
        System.out.println();

        System.out.println(positiveNumberToBinary(128));
    }

    public static double getTriangleSquare(int firstSide, int secondSide, int thirdSide) {
        try {
            if (firstSide <= 0 || secondSide <= 0 || thirdSide <= 0) {
                throw new IllegalArgumentException("Sides should be positive");
            }
            if (!isItTriangle(firstSide, secondSide, thirdSide)) {
                throw new ItIsNotTriangleException("Doesn't exist triangle with these sides");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (ItIsNotTriangleException e) {
            e.printStackTrace();
            System.exit(1);
        }
        double perimeter = 1.0 * (firstSide + secondSide + thirdSide) / 2;
        return Math.sqrt(perimeter
                * (perimeter - firstSide)
                * (perimeter - secondSide)
                * (perimeter - thirdSide));
    }

    private static boolean isItTriangle(int firstSide, int secondSide, int thirdSide) {
        return (firstSide < (secondSide + thirdSide))
                && (secondSide < (firstSide + thirdSide))
                && (thirdSide < (firstSide + secondSide));
    }

    public static int minRandomNumberInAbsolute() {
        int randomNumber1 = (int) (Math.random() * (200 + 1)) - 100;
        int randomNumber2 = (int) (Math.random() * (200 + 1)) - 100;
        int randomNumber3 = (int) (Math.random() * (200 + 1)) - 100;

        randomNumber1 = Math.abs(randomNumber1) < Math.abs(randomNumber2) ? randomNumber1 : randomNumber2;
        return Math.abs(randomNumber1) < Math.abs(randomNumber3) ? randomNumber1 : randomNumber3;
    }

    public static boolean isRandomNumberEven() {
        return minRandomNumberInAbsolute() % 2 == 0;
    }

    public static boolean isRandomNumberEven(int number) {
        return number % 2 == 0;
    }

    public static String positiveNumberToBinary(int number) {
        try {
            if (number < 0) {
                throw new IllegalArgumentException("Number should be positive");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "Try again";
        }
        if (number == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        String temp = String.format("Number %d in binary- ", number);
        while (number != 0) {
            sb.append(number % 2);
            number /= 2;
        }
        return temp + sb.reverse();
    }

    private static class ItIsNotTriangleException extends Exception {

        public ItIsNotTriangleException() {
        }

        public ItIsNotTriangleException(String message) {
            super(message);
        }
    }
}
