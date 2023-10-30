package hw3;

import edu.hw3.Task3_7;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test3_7 {
    @Test
    public void testNullKey() {

        TreeMap<String, String> tree = new TreeMap<>(
            Comparator.nullsFirst(Comparator.comparingInt(String::length))
        );
        tree.put(null, "test");
        assertTrue(tree.containsKey(null));
    }
}
