package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test8 {

    @Test
    public void testIsOddLenTrue() {
        assertTrue(Task8.isOddLen("101"));
        assertTrue(Task8.isOddLen("0"));
    }

    @Test
    public void testIsOddLenFalse() {
        assertFalse(Task8.isOddLen("11"));
        assertFalse(Task8.isOddLen("0101"));
    }


    @Test
    public void testZeroCountThreeTrue() {
        assertTrue(Task8.zeroCountThree("010101"));
        assertTrue(Task8.zeroCountThree("111"));
    }

    @Test
    public void testZeroCountThreeFalse() {
        assertFalse(Task8.zeroCountThree("11011"));
        assertFalse(Task8.zeroCountThree("100"));
    }

    @Test
    public void testExceptOneOneTrue() {
        assertTrue(Task8.exceptOneOne("00101"));
        assertTrue(Task8.exceptOneOne("10"));
    }

    @Test
    public void testExceptOneOneFalse() {
        assertFalse(Task8.exceptOneOne("11"));
        assertFalse(Task8.exceptOneOne("111"));
    }


    @Test
    public void testOneStickMoreEyesTrue() {
        assertTrue(Task8.oneStickMoreEyes("00000"));
        assertTrue(Task8.oneStickMoreEyes("000001"));
    }

    @Test
    public void testOneStickMoreEyesFalse() {
        assertFalse(Task8.oneStickMoreEyes("111"));
        assertFalse(Task8.oneStickMoreEyes("100101"));
    }

    @Test
    public void testNoSticksTrue() {
        assertTrue(Task8.noSticks("101010"));
        assertTrue(Task8.noSticks("0"));
    }

    @Test
    public void testNoSticksFalse() {
        assertFalse(Task8.noSticks("11011"));
        assertFalse(Task8.noSticks("10010011"));
    }
}
