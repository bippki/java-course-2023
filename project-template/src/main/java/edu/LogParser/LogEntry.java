package edu.LogParser;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LogEntry(
        LocalDate timestamp,
        String ip,
        String request,
        int statusCode,
        int responseSize,
        String referrer,
        String userAgent
) {}
