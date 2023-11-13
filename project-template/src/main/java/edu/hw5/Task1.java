package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Task1 {
    static String averageDuration(List<String> counter) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
        long totalSeconds = 0;
        for (String a: counter) {
            String[] parts = a.split(" - ");
            LocalDateTime startDateTime = LocalDateTime.parse(parts[0], formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(parts[1], formatter);
            Duration duration = Duration.between(startDateTime, endDateTime);
            totalSeconds += duration.getSeconds();
        }

        long averageSeconds = totalSeconds / counter.size();
        Duration averageDuration = Duration.ofSeconds(averageSeconds);

        long hours = averageDuration.toHours();
        long minutes = averageDuration.toMinutesPart();

        return String.format("%dч %02dм", hours, minutes);
    }
}
