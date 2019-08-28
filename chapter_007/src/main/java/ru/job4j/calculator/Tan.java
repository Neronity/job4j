package ru.job4j.calculator;

public class Tan implements TrigonoOperation {

    @Override
    public double execute(double value) {
        return Math.tan(value);
    }

    @Override
    public double execute(double value1, double value2) {
        return 0;
    }

    @Override
    public String getOperationKey() {
        return "tan";
    }

    @Override
    public String toString() {
        return "tan - calculate tangent for given number.";
    }
}
