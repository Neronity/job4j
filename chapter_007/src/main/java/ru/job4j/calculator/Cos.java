package ru.job4j.calculator;

public class Cos implements TrigonoOperation {

    @Override
    public double execute(double value) {
        return Math.cos(value);
    }

    @Override
    public double execute(double value1, double value2) {
        return 0;
    }

    @Override
    public String getOperationKey() {
        return "cos";
    }

    @Override
    public String toString() {
        return "cos - calculate cosine for given number.";
    }
}
