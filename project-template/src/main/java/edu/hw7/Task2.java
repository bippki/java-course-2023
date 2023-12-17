package edu.hw7;

import java.util.stream.LongStream;

public class Task2 {
    private Task2() {
    }

    public static long factorial(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("Число не должно быть меньше 0");
        }

        return LongStream.rangeClosed(1, n)
            .parallel()
            .reduce(1, (x, y) -> x * y);
    }

    public static void main(String[] args) {
        long number = 8;
        long result = factorial(number);
        System.out.println("Факториал числа " + number + " равен " + result);
    }
}
