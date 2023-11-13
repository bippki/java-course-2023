package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test1 {

    @Test
    public void testCalculateAndFormatAverageDuration() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

        List<String> durations = Arrays.asList(
            "2023-01-01, 10:00 - 2023-01-01, 11:00",
            "2023-01-01, 12:00 - 2023-01-01, 13:30",
            "2023-01-01, 15:45 - 2023-01-01, 17:30"
        );

        String result = Task1.averageDuration(durations);

        assertEquals("1ч 25м", result);
    }
}
