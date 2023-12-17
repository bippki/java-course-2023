package edu.hw10.Task10_2;

public class FibCalculatorImpl implements FibCalculator {
    @Override
    public long calculate(int number) {
        return fib(number);
    }
    @Override
    public long fib(int number) {
        if (number <= 1) {
            return number;
        }
        return fib(number - 1) + fib(number - 2);
    }
}
