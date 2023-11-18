package edu.LogParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import java.time.LocalDate;

public class LogAnalyzer {
    private static final Pattern LOG_PATTERN = Pattern.compile(
            "^(\\S+) - - \\[(.*?)\\] \"(\\S+ \\S+ \\S+)\" (\\d+) (\\d+) \"(.*?)\" \"(.*?)\"$"
    );

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);

    private static LogEntry parseLogEntry(String logLine) {
        Matcher matcher = LOG_PATTERN.matcher(logLine);

        if (matcher.matches()) {
            String ip = matcher.group(1);
            String timestampStr = matcher.group(2);

            String request = matcher.group(3);
            int statusCode = Integer.parseInt(matcher.group(4));
            int responseSize = Integer.parseInt(matcher.group(5));
            String referrer = matcher.group(6);
            String userAgent = matcher.group(7);

            LocalDate timestamp = LocalDate.parse(timestampStr, DATE_FORMATTER);

            return new LogEntry(timestamp, ip, request, statusCode, responseSize, referrer, userAgent);
        } else {
            return null;
        }
    }


    static List<LogEntry> readLogEntries(String pathOrUrl) throws IOException {
        try {
            if (pathOrUrl.endsWith("/") || pathOrUrl.endsWith("\\")) {
                return readFromUrlDirectory(pathOrUrl);
            }
            return readFromUrlFile(pathOrUrl);
        } catch (Exception e) {
            Path path = Paths.get(pathOrUrl);
            if (Files.isDirectory(path)) {
                return readLogEntriesFromDirectory(path);
            } else {
                return readLogEntriesFromFile(path);
            }
        }
    }
    private static List<LogEntry> readLogEntriesFromDirectory(Path directoryPath) throws IOException {
        return Files.list(directoryPath)
                .filter(Files::isRegularFile)
                .map(LogAnalyzer::readLogEntriesFromFile)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static List<LogEntry> readLogEntriesFromFile(Path filePath) {
        try {
            return Files.lines(filePath)
                    .map(LogAnalyzer::parseLogEntry)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error reading log file: " + filePath);
            e.printStackTrace();
            return List.of();
        }
    }

    static List<LogEntry> readFromUrlDirectory(String url) throws IOException {
        List<LogEntry> logEntries = new ArrayList<>();
        URL directoryUrl = new URL(url);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(directoryUrl.openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.endsWith(".log")) {
                    String fileUrl = directoryUrl + "/" + line;
                    List<LogEntry> entriesFromFile = readFromUrlFile(fileUrl);
                    logEntries.addAll(entriesFromFile);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading log files from URL directory: " + url, e);
        }

        return logEntries;
    }

    static List<LogEntry> readFromUrlFile(String url) throws IOException {
        List<LogEntry> logEntries = new ArrayList<>();
        URL fileUrl = new URL(url);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileUrl.openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LogEntry logEntry = parseLogEntry(line);
                logEntries.add(logEntry);
            }
        } catch (IOException e) {
            throw new IOException("Error reading log file from URL: " + url, e);
        }

        return logEntries;
    }

    static double getAverageResponseSize(List<LogEntry> logEntries, LocalDate fromDate, LocalDate toDate) {
        List<LogEntry> filteredEntries = logEntries.stream()
                .filter(entry -> isWithinDateRange(entry.timestamp(), fromDate, toDate))
                .toList();

        if (filteredEntries.isEmpty()) {
            return 0.0;
        }

        double totalResponseSize = filteredEntries.stream()
                .mapToLong(LogEntry::responseSize)
                .sum();

        return totalResponseSize / filteredEntries.size();
    }

    static Map<String, Long> getResourceRequests(List<LogEntry> logEntries, LocalDate fromDate, LocalDate toDate) {
        return logEntries.stream()
                .filter(entry -> isWithinDateRange(entry.timestamp(), fromDate, toDate))
                .collect(Collectors.groupingBy(LogEntry::request, Collectors.counting()));
    }
    static long getTotalRequests(List<LogEntry> logEntries, LocalDate fromDate, LocalDate toDate) {
        return logEntries.stream()
                .filter(entry -> isWithinDateRange(entry.timestamp(), fromDate, toDate))
                .count();
    }

    private static boolean isWithinDateRange(LocalDate date, LocalDate fromDate, LocalDate toDate) {
        return (fromDate == null || date.isAfter(fromDate) || date.equals(fromDate)) &&
                (toDate == null || date.isBefore(toDate) || date.equals(toDate));
    }

    static Map<Integer, Long> getResponseCodes(List<LogEntry> logEntries, LocalDate fromDate, LocalDate toDate) {
        Map<Integer, Long> responseCodes = new HashMap<>();

        logEntries.stream()
                .filter(entry -> isWithinDateRange(entry.timestamp(), fromDate, toDate))
                .forEach(entry -> responseCodes.merge(entry.statusCode(), 1L, Long::sum));

        return responseCodes;
    }

}
