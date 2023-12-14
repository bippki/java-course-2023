package edu.hw6;

import org.junit.jupiter.api.Test;
import static edu.hw6.Task6.Main.getServiceName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test6 {
    @Test
    void testGetServiceName() {
        assertEquals("ERMAR",getServiceName(135));
        assertEquals("TCP", getServiceName(5040));
        assertEquals("TCP", getServiceName(5939));
        assertEquals("TCP", getServiceName(6463));
        assertEquals("TCP", getServiceName(6942));
        assertEquals("TCP", getServiceName(17680));
        assertEquals("Unknown", getServiceName(17681));
    }
}
