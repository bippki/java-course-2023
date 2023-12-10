package edu.hw9;

import edu.hw9.Task1.MetricStats;
import edu.hw9.Task1.StatsCollector;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task9_1Test{

    @Test
    public void testStatsCollector() {
        StatsCollector collector = new StatsCollector();

        collector.push("metric1", new double[]{0.1, 0.05, 1.4, 5.1, 0.3});
        collector.push("metric2", new double[]{2.0, 3.5, 1.8, 0.9});

        List<MetricStats> stats = collector.stats();

        assertEquals(2, stats.size());

        MetricStats metric1Stats = stats.get(0);
        assertEquals("metric1", metric1Stats.getMetricName());
        assertEquals(6.95, metric1Stats.getSum(), 1e-15);
        assertEquals(1.39, metric1Stats.getAverage(), 0.001);
        assertEquals(0.05, metric1Stats.getMin(), 0.001);
        assertEquals(5.1, metric1Stats.getMax(), 0.001);

        MetricStats metric2Stats = stats.get(1);
        assertEquals("metric2", metric2Stats.getMetricName());
        assertEquals(8.2, metric2Stats.getSum(), 1e-15);
        assertEquals(2.05, metric2Stats.getAverage(), 0.001);
        assertEquals(0.9, metric2Stats.getMin(), 0.001);
        assertEquals(3.5, metric2Stats.getMax(), 0.001);
    }
}
