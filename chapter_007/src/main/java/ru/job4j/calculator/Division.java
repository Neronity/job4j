package ru.job4j.calculator;

/**
 * Division operation
 */
public class Division implements Operation {

    @Override
    public double execute(double dividend, double divider) {
        return dividend / divider;
    }

    @Override
    public String getOperationKey() {
        return "/";
    }

    @Override
    public String toString() {
        return String.format("%s - division operation.", this.getOperationKey());
    }
}
