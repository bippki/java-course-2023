package edu.hw8.Task2;



public class Main {
    private Main(){}
    public static void main(String[] args) {
        try (ThreadPool threadPool = new FixedThreadPool(3)) {
            threadPool.start();
            for (int i = 0; i < 31; i++) {
                int n = i;
                threadPool.execute(() -> {
                    int result = calcFib(n);
                    System.out.println("Fibonacci(" + n + ") = " + result);
                });
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int calcFib(int n) {
        if (n <= 1) {
            return n;
        }
        return calcFib(n - 1) + calcFib(n - 2);
    }
}
