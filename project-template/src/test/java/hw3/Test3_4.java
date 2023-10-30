package hw3;

import edu.hw3.Task3_4;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test3_4 {

    @Test
    void testRomanNumbers() {
        assertEquals("I", Task3_4.convertToRoman(1));
        assertEquals("IV", Task3_4.convertToRoman(4));
        assertEquals("V", Task3_4.convertToRoman(5));
        assertEquals("CCCLII", Task3_4.convertToRoman(352));
        assertEquals("MLIX", Task3_4.convertToRoman(1059));
        assertEquals("CMXL", Task3_4.convertToRoman(940));
        assertEquals("DCLXVI", Task3_4.convertToRoman(666));
        assertEquals("MMMCMLXXXIV", Task3_4.convertToRoman(3984));
        assertEquals("LIX", Task3_4.convertToRoman(59));
        assertEquals("MMMCMXCIX", Task3_4.convertToRoman(3999));
    }
}
