package ru.job4j.calculator;

import java.util.List;
import java.util.Optional;

public class TrigonoCalculator extends Calculator {

    /**
     * Creates calculator with given operations
     *
     * @param operations list of operations used
     */
    public TrigonoCalculator(List<Operation> operations) {
        super(operations);
    }

    public double calculate(double value, String operation) throws IllegalArgumentException {
        this.prevResult = ((TrigonoOperation) getOperByKey(operation)).execute(value);
        return this.prevResult;
    }

    @Override
    public double calculate(double value1, double value2, String operation) throws IllegalArgumentException {
        this.prevResult = getOperByKey(operation).execute(value1, value2);
        return this.prevResult;
    }


    public Operation getOperByKey(String operation) throws IllegalArgumentException {
        Operation op = Optional.ofNullable(
                this.operations.get(operation))
                .orElseThrow(() -> new IllegalArgumentException("No such operation.")
                );
        this.prevOperation.setPrevOperation(op);
        return this.prevOperation;
    }
}
