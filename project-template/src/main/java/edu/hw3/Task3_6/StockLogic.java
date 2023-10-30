package edu.hw3.Task3_6;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StockLogic implements StockInterface {
    private PriorityQueue<Stock> stockPriorityQueue;

    public StockLogic() {
        stockPriorityQueue =new PriorityQueue<>(Comparator.comparingDouble(Stock::getPrice));
    }

    @Override
    public void add(Stock stock) {
        stockPriorityQueue.offer(stock);
    }

    @Override
    public void remove(Stock stock) {
        stockPriorityQueue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stockPriorityQueue.peek();
    }
}
