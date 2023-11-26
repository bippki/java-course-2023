package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1{

    private Task1(){}
    static final int NUM_THREADS = 4;
    static final int NUM_INCREMENTS = 1;
    static AtomicInteger counter = new AtomicInteger(0);
    public static void main(String[] args) {
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new IncrementThread();
            threads[i].start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Итоговое значение: " + counter.get());
    }

    static class IncrementThread extends Thread {
        @Override
        public void run() {
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < NUM_INCREMENTS; i++) {
                counter.incrementAndGet();
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Поток " + Thread.currentThread().getId() + " работал " + (endTime - startTime) + " миллисекунд");
        }
    }
}
