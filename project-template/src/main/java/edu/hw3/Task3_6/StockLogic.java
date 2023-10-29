package edu.hw3.Task3_6;

import java.util.PriorityQueue;

public class StockLogic implements StockInterface {
    private PriorityQueue<Stock> stockPriorityQueue;

    public StockLogic() {
        stockPriorityQueue = new PriorityQueue<>((stock1, stock2) -> Double.compare(stock2.getPrice(), stock1.getPrice()));
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
