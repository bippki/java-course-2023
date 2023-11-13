package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test7 {

    @Test
    public void test3zeroValid() {
        assertTrue(Task7.thirdSymbZero("0101"));
        assertTrue(Task7.thirdSymbZero("10001"));
    }

    @Test
    public void test3zeroInValid() {
        assertFalse(Task7.thirdSymbZero("01"));
        assertFalse(Task7.thirdSymbZero("1010"));
    }

    @Test
    public void testStartsEndValid() {
        assertTrue(Task7.sameStartAndEnd("010"));
        assertTrue(Task7.sameStartAndEnd("111"));
    }

    @Test
    public void testStartsEndInValid() {
        assertFalse(Task7.sameStartAndEnd("0101"));
        assertFalse(Task7.sameStartAndEnd("1010"));
    }

    @Test
    public void testOneToThreeValid() {
        assertTrue(Task7.totallySpice("0"));
        assertTrue(Task7.totallySpice("01"));
        assertTrue(Task7.totallySpice("010"));
    }

    @Test
    public void testOneToThreeInValid() {
        assertFalse(Task7.totallySpice(""));
        assertFalse(Task7.totallySpice("0101"));
        assertFalse(Task7.totallySpice("01010"));
    }
}
