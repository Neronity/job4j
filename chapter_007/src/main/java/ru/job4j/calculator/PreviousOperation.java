package ru.job4j.calculator;

import java.util.Optional;

/**
 * Wrapper class for any operation. Сan not contain an instance of itself.
 */
public class PreviousOperation implements TrigonoOperation {

    private Operation prevOperation;

    /**
     * Previous operation won't change in case param is instance of this class.
     * @param prevOperation operation for containing
     */
    public void setPrevOperation(Operation prevOperation) {
        if (!(prevOperation instanceof PreviousOperation)) this.prevOperation = prevOperation;
    }

    /**
     *
     * @param value1 first value for operation
     * @param value2 second value for operation
     * @return result of execution of wrapped operation.
     * @throws IllegalArgumentException in case non existent wrapped operation
     */
    @Override
    public double execute(double value1, double value2) throws IllegalArgumentException {
        return Optional.ofNullable(this.prevOperation)
                .orElseThrow(() -> new IllegalArgumentException("There is no previous operation."))
                .execute(value1, value2);
    }

    @Override
    public String getOperationKey() {
        return this.prevOperation.getOperationKey();
    }

    @Override
    public String toString() {
        return "prev - last used operation.";
    }


    @Override
    public double execute(double value) {
        return ((TrigonoOperation) Optional.ofNullable(this.prevOperation)
                .orElseThrow(() -> new IllegalArgumentException("There is no previous operation.")))
                .execute(value);
    }
}
