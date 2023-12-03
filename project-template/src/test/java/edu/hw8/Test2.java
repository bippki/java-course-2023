package edu.hw8;

import edu.hw8.Task2.FixedThreadPool;
import edu.hw8.Task2.Main;
import edu.hw8.Task2.ThreadPool;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class Test2 {

    @Test
    void testThred() throws InterruptedException {
        int threadCount = 4;
        CountDownLatch latch = new CountDownLatch(threadCount);

        try (ThreadPool threadPool = new FixedThreadPool(threadCount)) {
            threadPool.start();

            for (int i = 0; i < threadCount; i++) {
                int n = i;
                threadPool.execute(() -> {
                    latch.countDown();
                });
            }

            latch.await(500, TimeUnit.MILLISECONDS);
            assertEquals(0, latch.getCount());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testFib() {
        assertEquals(75025, Main.calcFib(25));
        assertEquals(121393, Main.calcFib(26));
        assertEquals(196418, Main.calcFib(27));
        assertEquals(317811, Main.calcFib(28));
    }
}
