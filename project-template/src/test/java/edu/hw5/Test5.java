package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test5 {

    @Test
    public void testValidPassword() {
        assertTrue(Task5.regexAutoNumber("А123СВ456"));
        assertTrue(Task5.regexAutoNumber("М123СТ012"));
    }

    @Test
    public void testInValidPassword() {
        assertFalse(Task5.regexAutoNumber("А123БВ45"));
        assertFalse(Task5.regexAutoNumber("А123БВ45Г"));
        assertFalse(Task5.regexAutoNumber("123БВ456"));
    }
}
