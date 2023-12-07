package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class Task2 {

    public static void findFri13(int year) {
        for (int month = 1; month <= 12; month++) {
            LocalDate date = LocalDate.of(year, month, 13);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                System.out.println("Friday the 13th: " + date);
            }
        }
    }

    public static LocalDate findDateFri13(LocalDate currentDate) {
        TemporalAdjuster nextFriday13th = temporal -> {
            LocalDate date = (LocalDate) temporal;
            int daysToAdd = 1;
            while (date.plusDays(daysToAdd).getDayOfMonth() != 13 || date.plusDays(daysToAdd).getDayOfWeek() != DayOfWeek.FRIDAY) {
                daysToAdd++;
            }
            return date.plusDays(daysToAdd);
        };
        return currentDate.with(nextFriday13th);
    }
}
