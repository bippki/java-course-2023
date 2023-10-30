package hw3;

import edu.hw3.Task3_6.Stock;
import edu.hw3.Task3_6.StockInterface;
import edu.hw3.Task3_6.StockLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Test3_6 {

    private StockInterface stockMarket;

    @BeforeEach
    void setUp() {
        stockMarket = new StockLogic();
    }

    @Test
    void testHighValue() {
        Stock apple = new Stock("APPLE", 1000);
        Stock google = new Stock("GOOGLE", 2000);
        Stock tesla = new Stock("TESLA", 500);

        stockMarket.add(apple);
        stockMarket.add(google);
        stockMarket.add(tesla);

        assertEquals("GOOGLE", stockMarket.mostValuableStock().getName());
    }

    @Test
    void testRemoveMostValuableStock() {
        Stock apple = new Stock("APPLE", 1000);
        Stock google = new Stock("GOOGLE", 2000);
        Stock tesla = new Stock("TESLA", 500);

        stockMarket.add(apple);
        stockMarket.add(google);
        stockMarket.add(tesla);

        stockMarket.remove(google);
        assertEquals("APPLE", stockMarket.mostValuableStock().getName());
    }

    @Test
    void testEmpty() {
        assertNull(stockMarket.mostValuableStock());
    }
}
