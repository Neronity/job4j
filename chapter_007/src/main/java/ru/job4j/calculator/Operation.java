package ru.job4j.calculator;

/**
 * Operations interface
 */
public interface Operation {

    /**
     * Performing certain action on given values
     * @param value1 first value for operation
     * @param value2 second value for operation
     * @return Result of operation
     */
    double execute(double value1, double value2);

    /**
     * Getting key for using with map.
     * @return text key for operation
     */
    String getOperationKey();
}
