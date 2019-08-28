package ru.job4j.calculator;

/**
 * Multiplication operation
 */
public class Multiplication implements Operation {

    @Override
    public double execute(double multiplicand, double multiplier) {
        return multiplicand * multiplier;
    }

    @Override
    public String getOperationKey() {
        return "*";
    }

    @Override
    public String toString() {
        return String.format("%s - multiplication operation.", this.getOperationKey());
    }
}
