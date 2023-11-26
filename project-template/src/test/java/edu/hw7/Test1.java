package edu.hw7;


import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test1 {

    @Test
    public void testCounter() throws InterruptedException {
        Task1.main(new String[0]);
        assertEquals(Task1.NUM_THREADS * Task1.NUM_INCREMENTS, Task1.counter.get());
    }

    @Test
    public void testIncr() throws InterruptedException {
        int numThreads = 4;
        int numIncrements = 100000;
        AtomicInteger counter = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(numThreads);
        for (int i = 0; i < numThreads; i++) {
            new Thread(() -> {
                for (int j = 0; j < numIncrements; j++) {
                    counter.incrementAndGet();
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        assertEquals(numThreads * numIncrements, counter.get());
    }
}
