package hw3;

import edu.hw3.Task3_3;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

public class Test3_3 {
    @Test
    public void testOrdinal() {
        List<String> input = List.of("кот", "пёс", "кот", "кот");
        Map<String, Integer> output = Task3_3.freqDict(input);
        Map<String, Integer> expected3 = Map.of("кот", 3, "пёс", 1);
        assertEquals(expected3, output);
    }

    @Test
    public void testDictIntegers() {
        List<Integer> input = List.of(1, 1, 2, 3, 2, 2);
        Map<Integer, Integer> output = Task3_3.freqDict(input);
        Map<Integer, Integer> expected = Map.of(1, 2, 2, 3, 3, 1);
        assertEquals(expected, output);
    }
}
