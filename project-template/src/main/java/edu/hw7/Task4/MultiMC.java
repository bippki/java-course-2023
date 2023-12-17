package edu.hw7.Task4;

import java.util.concurrent.*;

public class MultiMC {
    private MultiMC(){}
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java MultiMC <number_of_iterations> <number_of_threads>");
            System.exit(1);
        }
        int N = Integer.parseInt(args[0]);
        int numThreads = Integer.parseInt(args[1]);

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        long startTime = System.currentTimeMillis();

        try {
            int totalCount = 0;
            int circleCount = 0;

            CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);

            for (int i = 0; i < numThreads; i++) {
                completionService.submit(new MonteCarloTask(N / numThreads, circleCount, totalCount));
            }

            for (int i = 0; i < numThreads; i++) {
                Future<Integer> future = completionService.take();
                circleCount += future.get();
                totalCount += N / numThreads;
            }

            double piCount = 4.0 * circleCount / totalCount;

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            System.out.println("Приближение числа Пи: " + piCount);
            System.out.println("Сравнение с реальным значением Пи: " + Math.abs(Math.PI - piCount));
            System.out.println("Время выполнения: " + executionTime + " миллисекунд");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}

class MonteCarloTask implements Callable<Integer> {
    private final int iterations;
    private final int circleCount;
    private final int totalCount;

    public MonteCarloTask(int iterations, int circleCount, int totalCount) {
        this.iterations = iterations;
        this.circleCount = circleCount;
        this.totalCount = totalCount;
    }

    @Override
    public Integer call() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int localCircleCount = 0;

        for (int i = 0; i < iterations; i++) {
            double x = random.nextDouble() * 2 - 1;
            double y = random.nextDouble() * 2 - 1;

            if (circlePoint(x, y)) {
                localCircleCount++;
            }
        }

        return localCircleCount;
    }

    private static boolean circlePoint(double x, double y) {
        return x * x + y * y <= 1;
    }
}
