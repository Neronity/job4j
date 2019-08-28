package ru.job4j.calculator;

/**
 * Addition operation
 */
public class Sum implements Operation {

    @Override
    public double execute(double firstAddendum, double secondAddendum) {
        return firstAddendum + secondAddendum;
    }

    @Override
    public String getOperationKey() {
        return "+";
    }

    @Override
    public String toString() {
        return String.format("%s - addition operation.", this.getOperationKey());
    }
}
