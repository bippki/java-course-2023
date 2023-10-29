package hw3;
import edu.hw3.Task3_8;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test3_8 {
    @Test
    void testIterator() {
        Iterator<Integer> it = new Task3_8<>(List.of(1, 2, 3));
        ArrayList<Integer> al = Lists.newArrayList(it);
        assertEquals(al, List.of(3, 2, 1));
    }
}
