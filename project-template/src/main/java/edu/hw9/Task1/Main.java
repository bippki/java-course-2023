package edu.hw9.Task1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        edu.hw9.Task1.StatsCollector collector = new edu.hw9.Task1.StatsCollector();

        collector.push("metric1", new double[]{0.1, 0.05, 1.4, 5.1, 0.3});
        collector.push("metric2", new double[]{2.0, 3.5, 1.8, 0.9});

        List<edu.hw9.Task1.MetricStats> stats = collector.stats();

        for (edu.hw9.Task1.MetricStats metricStats : stats) {
            System.out.println("Metric: " + metricStats.metricName());
            System.out.println("Sum: " + metricStats.sum());
            System.out.println("Average: " + metricStats.average());
            System.out.println("Min: " + metricStats.min());
            System.out.println("Max: " + metricStats.max());
            System.out.println();
        }
    }
}
