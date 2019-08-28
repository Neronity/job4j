package ru.job4j.calculator;

public class Sin implements TrigonoOperation {
    @Override
    public double execute(double value) {
        return Math.sin(value);
    }

    @Override
    public double execute(double value1, double value2) {
        return 0;
    }

    @Override
    public String getOperationKey() {
        return "sin";
    }

    @Override
    public String toString() {
        return "sin - calculate sinus for given number.";
    }
}
