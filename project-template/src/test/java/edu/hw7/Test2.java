package edu.hw7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test2 {


    @Test
    public void testNumber() {
        long result = Task2.factorial(10);
        assertEquals(3628800, result);
    }

    @Test
    public void testZero() {
        long result = Task2.factorial(0);
        assertEquals(1, result);
    }


    @Test
    public void testNegNum() {
        try {
            Task2.factorial(-5);
        } catch (IllegalArgumentException e) {
            assertEquals("Число не должно быть меньше 0", e.getMessage());
        }
    }
}
