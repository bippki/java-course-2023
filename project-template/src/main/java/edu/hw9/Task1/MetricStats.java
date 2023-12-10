package edu.hw9.Task1;

import lombok.Getter;

public record MetricStats(@Getter String metricName, @Getter double sum, @Getter double average, @Getter double min, @Getter double max) {
}
