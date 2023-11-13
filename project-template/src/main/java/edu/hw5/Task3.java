package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Optional;

public class Task3 {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("M/d/yyyy");
    private static final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter SHORT_YEAR_FORMATTER = DateTimeFormatter.ofPattern("M/d/yy");


    public Task3() {
    }

    public static Task3 createDefaultParser() {
        return new Task3();
    }

    public Optional<LocalDate> parseDate(String string) {
        DateParserHandler chain = new CustomFormatHandler(
            new TomorrowHandler(
                new TodayHandler(
                    new YesterdayHandler(
                        new DayAgoHandler(
                            new DefaultHandler()
                        )
                    )
                )
            )
        );
        return chain.handleRequest(string);
    }

    private interface DateParserHandler {
        Optional<LocalDate> handleRequest(String input);
    }

    private static class CustomFormatHandler implements DateParserHandler {
        private final DateParserHandler successor;
        private static final DateTimeFormatter CUSTOM_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("[yyyy-MM-d][yyyy-MM-dd][M/d/uu][M/d/u]")
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .toFormatter();

        public CustomFormatHandler(DateParserHandler successor) {
            this.successor = successor;
        }

        @Override
        public Optional<LocalDate> handleRequest(String input) {
            try {
                LocalDate date = LocalDate.parse(input, CUSTOM_FORMATTER);
                return Optional.of(date);
            } catch (Exception e) {
                return successor.handleRequest(input);
            }
        }
    }

    private static class TomorrowHandler implements DateParserHandler {
        private final DateParserHandler successor;

        public TomorrowHandler(DateParserHandler successor) {
            this.successor = successor;
        }

        @Override
        public Optional<LocalDate> handleRequest(String input) {
            if ("tomorrow".equalsIgnoreCase(input)) {
                return Optional.of(LocalDate.now().plusDays(1));
            } else {
                return successor.handleRequest(input);
            }
        }
    }

    private static class TodayHandler implements DateParserHandler {
        private final DateParserHandler successor;

        public TodayHandler(DateParserHandler successor) {
            this.successor = successor;
        }

        @Override
        public Optional<LocalDate> handleRequest(String input) {
            if ("today".equalsIgnoreCase(input)) {
                return Optional.of(LocalDate.now());
            } else {
                return successor.handleRequest(input);
            }
        }
    }

    private static class YesterdayHandler implements DateParserHandler {
        private final DateParserHandler successor;

        public YesterdayHandler(DateParserHandler successor) {
            this.successor = successor;
        }

        @Override
        public Optional<LocalDate> handleRequest(String input) {
            if ("yesterday".equalsIgnoreCase(input)) {
                return Optional.of(LocalDate.now().minusDays(1));
            } else {
                return successor.handleRequest(input);
            }
        }
    }

    private static class DayAgoHandler implements DateParserHandler {
        private final DateParserHandler successor;

        public DayAgoHandler(DateParserHandler successor) {
            this.successor = successor;
        }

        @Override
        public Optional<LocalDate> handleRequest(String input) {
            if (input.matches("\\d+\\s+day[s]?\\s+ago")) {
                int daysAgo = Integer.parseInt(input.split("\\s+")[0]);
                return Optional.of(LocalDate.now().minusDays(daysAgo));
            } else {
                return successor.handleRequest(input);
            }
        }
    }

    private static class DefaultHandler implements DateParserHandler {
        @Override
        public Optional<LocalDate> handleRequest(String input) {
            try {
                LocalDate date = LocalDate.parse(input, DATE_FORMATTER);
                return Optional.of(date);
            } catch (Exception e) {
                return Optional.empty();
            }
        }
    }

}
