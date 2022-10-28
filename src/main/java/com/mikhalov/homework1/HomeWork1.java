package com.mikhalov.homework1;


public class HomeWork1 {

    public static void main(String[] args) {

        printMyName();
        System.out.println();
        printLoopSteps();
        System.out.println();
        printAnotherLoop();

    }


    public static void printMyName() {
        System.out.println("Mikhalov Ivan");
    }

    public static void printLoopSteps() {
        for (int y = 5, i = 0; i < 11; y += 2, i++) {
            System.out.printf("Step %d, value %d\n", i, y);
        }
    }

    public static void printAnotherLoop() {

        for (int i = 0; i < 11; i++) {
            if (i == 3) {
                continue;
            }if (i == 6) {
                break;
            }
            System.out.println("Step " + i);
        }

    }


}
