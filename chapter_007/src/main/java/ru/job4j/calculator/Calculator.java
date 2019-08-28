package ru.job4j.calculator;

import java.util.*;

/**
Executing operations
 */
public class Calculator {
    private final Map<String, Operation> operations = new HashMap<>();
    private PreviousOperation prevOperation;
    private Double prevResult;

    /**
     * @return key of the previous operation (no need for optional: null will be caught before this code execution)
     */
    public String getPrevOperation() {
        return this.prevOperation.getOperationKey();
    }

    /**
     * @return Result of a previously executed operation if exists
     * @throws IllegalArgumentException in case of null value of prevOperation field
     */
    public double getPrevResult() throws IllegalArgumentException{
        return Optional.ofNullable(this.prevResult)
                .orElseThrow(() -> new IllegalArgumentException("There is no previous result."));
    }

    /**
     * @return values of this.operations map
     */
    public Collection<Operation> getOperations() {
        return this.operations.values();
    }

    /**
     * Creates calculator with given operations
     * @param operations list of operations used
     */
    public Calculator(List<Operation> operations) {
        this.prevOperation = new PreviousOperation();
        this.operations.put("prev", this.prevOperation);
        operations.forEach(o -> this.operations.put(o.getOperationKey(), o));
    }

    /**
     *
     * @param value1 first value for operation
     * @param value2 second value for operation
     * @param operation key to navigate Operation object in this.operations map
     * @return result of operation execution
     * @throws IllegalArgumentException if operation was not found in this.operations map
     */
    public double calculate(double value1, double value2, String operation) throws IllegalArgumentException {
        Operation op = Optional.ofNullable(
                this.operations.get(operation))
                .orElseThrow(() -> new IllegalArgumentException("No such operation.")
                );
        this.prevOperation.setPrevOperation(op);
        this.prevResult = op.execute(value1, value2);
        return this.prevResult;
    }

}
