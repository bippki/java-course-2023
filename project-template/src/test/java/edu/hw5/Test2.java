package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Friday13thTest {

    @Test
    void findFri13() {
        int year = 2024; // измените на ваш год
        String expectedOutput = "Friday the 13th: 2024-09-13\n" +
            "Friday the 13th: 2024-12-13\n";
        TestUtils.redirectSystemOut(() -> Task2.findFri13(year));
        assertEquals(expectedOutput, TestUtils.getSystemOut());
    }

    @Test
    void findDateFri13() {
        LocalDate currentDate = LocalDate.of(2023, 11, 12);
        LocalDate expectedDate = LocalDate.of(2024, 9, 13);

        assertEquals(expectedDate, Task2.findDateFri13(currentDate));
    }
}

class TestUtils {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final StringBuilder SYSTEM_OUT_CONTENT = new StringBuilder();

    static void redirectSystemOut(Runnable code) {
        var originalOut = System.out;
        try {
            System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
                @Override
                public void write(int b) {
                    SYSTEM_OUT_CONTENT.append((char) b);
                }
            }));
            code.run();
        } finally {
            System.setOut(originalOut);
        }
    }
    static String getSystemOut() {
        return SYSTEM_OUT_CONTENT.toString().replace(LINE_SEPARATOR, "\n");
    }
}
