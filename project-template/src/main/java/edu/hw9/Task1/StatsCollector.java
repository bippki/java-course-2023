package edu.hw9.Task1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class StatsCollector {
    private final ConcurrentHashMap<String, List<Double>> data;

    public StatsCollector() {
        this.data = new ConcurrentHashMap<>();
    }

    public void push(String metricName, double[] values) {
        data.compute(metricName, (key, existingValues) -> {
            if (existingValues == null) {
                existingValues = new ArrayList<>();
            }
            for (double value : values) {
                existingValues.add(value);
            }
            return existingValues;
        });
    }

    public List<MetricStats> stats() {
        List<Future<MetricStats>> futures;
        try (ExecutorService executorService = Executors.newFixedThreadPool(data.size())) {
            futures = new ArrayList<>();

            for (String metricName : data.keySet()) {
                List<Double> values = data.get(metricName);
                Callable<MetricStats> callable = () -> calculateStats(metricName, values);
                futures.add(executorService.submit(callable));
            }

            executorService.shutdown();
        }

        List<MetricStats> result = new ArrayList<>();
        for (Future<MetricStats> future : futures) {
            try {
                result.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private MetricStats calculateStats(String metricName, List<Double> values) {
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal min = new BigDecimal(Double.MAX_VALUE);
        BigDecimal max = new BigDecimal(Double.MIN_VALUE);

        for (double value : values) {
            BigDecimal bdValue = BigDecimal.valueOf(value);
            sum = sum.add(bdValue);
            min = min.min(bdValue);
            max = max.max(bdValue);
        }

        BigDecimal size = BigDecimal.valueOf(values.size());
        double average = sum.divide(size, 2, RoundingMode.HALF_UP).doubleValue();

        return new MetricStats(metricName, sum.doubleValue(), average, min.doubleValue(), max.doubleValue());
    }
}
