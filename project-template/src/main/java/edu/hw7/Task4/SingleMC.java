package edu.hw7.Task4;

import java.util.concurrent.ThreadLocalRandom;

public class SingleMC {
    private SingleMC() {
    }
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java SingleMC <number_of_iterations>");
            System.exit(1);
        }

        int N = Integer.parseInt(args[0]);
        int totalCount = 0;
        int circleCount = 0;

        ThreadLocalRandom random = ThreadLocalRandom.current();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < N; i++) {
            double x = random.nextDouble() * 2 - 1;
            double y = random.nextDouble() * 2 - 1;

            if (circlePoint(x, y)) {
                circleCount++;
            }

            totalCount++;
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        double piApproximation = 4.0 * circleCount / totalCount;

        System.out.println("Приближение числа Пи: " + piApproximation);
        System.out.println("Сравнение с реальным значением Пи: " + Math.abs(Math.PI - piApproximation));
        System.out.println("Время выполнения: " + executionTime + " миллисекунд");
    }

    private static boolean circlePoint(double x, double y) {
        return x * x + y * y <= 1;
    }
}
