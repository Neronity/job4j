package ru.job4j.calculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Arrays;

/**
 * User interaction with calculator
 */
public class InteractCalculator implements AutoCloseable {
    protected final static Logger LOG = LogManager.getLogger();
    protected final Calculator calc;
    protected final PrintStream out;
    protected final InputStream in;

    /**
     * @param calc calculator, which will calculate user requests
     * @param out output method (System.out by default in main method)
     * @param in input method (System.in by default in main method)
     */
    public InteractCalculator(Calculator calc, PrintStream out, InputStream in) {
        this.calc = calc;
        this.out = out;
        this.in = in;
    }

    @Override
    public void close() throws Exception {
        this.out.close();
        this.in.close();
    }

    /**
     * Shows help (iterates over operations list in calculator)
     */
    protected void showHelp() {
        this.out.println("Calculation format: \'value1 operation value2\'");
        this.out.println("Example calculation: \'4 div 2\'.");
        this.out.println("Type \'res\' instead of value to use last calculated value.");
        this.out.println("exit - close program");
        this.calc.getOperations().forEach(o -> this.out.println(o.toString()));
    }

    /**
     * Shows calculated result for given user request.
     * Also show previously used operation.
     * @throws IllegalArgumentException in case of invalid data
     */
    protected void showResult(double d) {
        this.out.println(String.format("Result: %s", d));
        this.out.println(String.format("Last used operation: %s", this.calc.getPrevOperation()));
    }

    /**
     * Main loop. Runs until word "exit" is typed.
     */
    public void startMainLoop() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.in))) {
            showHelp();
            String userInput;
            while (!"exit".equals(userInput = reader.readLine())) {
                acceptInput(userInput);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Checks user input and shows result if request was valid
     * @param input raw user input
     */
    protected void acceptInput(String input) throws IllegalArgumentException {
        try {
            String[] sArr = input.split(" ");
            if (sArr[0].equals("res")) {
                sArr[0] = String.valueOf(this.calc.getPrevResult());
            }
            if (sArr[2].equals("res")) {
                sArr[2] = String.valueOf(this.calc.getPrevResult());
            }
            showResult(this.calc.calculate(Double.parseDouble(sArr[0]), Double.parseDouble(sArr[2]), sArr[1]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            this.out.println("Wrong values.");
        } catch (IllegalArgumentException e) {
            this.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try (InteractCalculator i = new InteractCalculator(new Calculator(
                Arrays.asList(
                        new Sum(),
                        new Division(),
                        new Subtraction(),
                        new Multiplication()
                )), System.out, System.in)
        ) {
            i.startMainLoop();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
