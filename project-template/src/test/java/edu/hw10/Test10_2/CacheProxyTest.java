package edu.hw10.Test10_2;

import edu.hw10.Task10_2.CacheProxy;
import edu.hw10.Task10_2.FibCalculator;
import edu.hw10.Task10_2.FibCalculatorImpl;
import java.io.File;
import java.lang.reflect.Proxy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CacheProxyTest {

    private static final String CACHE_DIRECTORY = "test_cache";
    private static final int TEST_NUMBER = 5;

    private FibCalculator fibCalculator;
    private FibCalculator fibCalculatorProxy;

    @Before
    public void setUp() {
        fibCalculator = new FibCalculatorImpl();
        fibCalculatorProxy = CacheProxy.create(fibCalculator, FibCalculator.class, CACHE_DIRECTORY);
    }

    @After
    public void tearDown() {
        CacheProxy cacheProxy = (CacheProxy) Proxy.getInvocationHandler(fibCalculatorProxy);
        cacheProxy.clearCache();
    }

    @Test
    public void testCaching() {
        long result1 = fibCalculatorProxy.calculate(TEST_NUMBER);

        assertEquals(result1, fibCalculator.fib(TEST_NUMBER));

        long result2 = fibCalculatorProxy.calculate(TEST_NUMBER);

        assertEquals(result1, result2);

        File cacheDirectory = new File(CACHE_DIRECTORY);
        File[] cacheFiles = cacheDirectory.listFiles();
        assertEquals(1, cacheFiles.length);

        CacheProxy cacheProxy = (CacheProxy) Proxy.getInvocationHandler(fibCalculatorProxy);
        cacheProxy.clearCache();
        cacheFiles = cacheDirectory.listFiles();
        assertEquals(0, cacheFiles.length);
    }
}
