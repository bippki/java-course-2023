package edu.hw5;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test3 {

    @Test
    void testParseDate() {
        Task3 parser = new Task3();

        assertParsedDate(parser, "2020-10-10", LocalDate.of(2020, 10, 10));
        assertParsedDate(parser, "2020-12-02", LocalDate.of(2020, 12, 2));
        assertParsedDate(parser, "1/3/1976", LocalDate.of(1976, 1, 3));
        assertParsedDate(parser, "1/3/20", LocalDate.of(2020, 1, 3));
        assertParsedDate(parser, "tomorrow", LocalDate.now().plusDays(1));
        assertParsedDate(parser, "today", LocalDate.now());
        assertParsedDate(parser, "yesterday", LocalDate.now().minusDays(1));
        assertParsedDate(parser, "1 day ago", LocalDate.now().minusDays(1));
        assertParsedDate(parser, "2234 days ago", LocalDate.now().minusDays(2234));
        assertEmpty(parser, "invalid date");
        assertEmpty(parser, "not a date");
    }

    private void assertParsedDate(Task3 parser, String input, LocalDate expectedDate) {
        Optional<LocalDate> result = parser.parseDate(input);
        assertTrue(result.isPresent());
        assertEquals(expectedDate, result.get());
    }

    private void assertEmpty(Task3 parser, String input) {
        Optional<LocalDate> result = parser.parseDate(input);
        assertTrue(result.isEmpty());
    }
}
