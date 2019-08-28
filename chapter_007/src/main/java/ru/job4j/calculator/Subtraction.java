package ru.job4j.calculator;

/**
 * Subtraction operation
 */
public class Subtraction implements Operation {

    @Override
    public double execute(double minuend, double subtrahend) {
        return minuend - subtrahend;
    }

    @Override
    public String getOperationKey() {
        return "-";
    }

    @Override
    public String toString() {
        return String.format("%s - subtraction operation.", this.getOperationKey());
    }
}
