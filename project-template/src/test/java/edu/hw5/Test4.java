package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Test4 {

    @Test
    void testValid() {
        assertTrue(Task4.regular("1234@"));
        assertTrue(Task4.regular("!longworm!"));
        assertTrue(Task4.regular("jingle*star"));
    }

    @Test
    void testInvalid() {
        assertFalse(Task4.regular("pozdno123"));
        assertFalse(Task4.regular("pit444borjomi"));
        assertFalse(Task4.regular("pochkiotkazali"));
    }

}
