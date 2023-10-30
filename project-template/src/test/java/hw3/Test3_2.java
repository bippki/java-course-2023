package hw3;

import edu.hw3.Task3_2;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test3_2 {

    @Test
    void testClusterizeBalancedString() {
        String input = "((()))(())()()(()())";
        List<String> expected = List.of("((()))", "(())", "()", "()", "(()())");
        List<String> result = Task3_2.clusterize(input);
        assertEquals(expected, result);
    }

    @Test
    void testClusterizeSingleCluster() {
        String input = "((()))";
        List<String> expected = List.of("((()))");
        List<String> result = Task3_2.clusterize(input);
        assertEquals(expected, result);
    }

    @Test
    void testClusterizeUnbalancedString() {
        String input = "((())())(()(()()))(";
        assertThrows(IllegalArgumentException.class, () -> Task3_2.clusterize(input));
    }

    @Test
    void testClusterizeExcessClosingParentheses() {
        String input = "(()))(";
        assertThrows(IllegalArgumentException.class, () -> Task3_2.clusterize(input));
    }
    }
