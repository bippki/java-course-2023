package edu.hw10.Task10_2;

public interface FibCalculator {
    @Cache(persist = true)
    long fib(int number);
    @Cache(persist = true)
    long calculate(int number);
}
