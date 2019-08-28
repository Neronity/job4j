package ru.job4j.calculator;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class TrigonoInteractCalculator extends InteractCalculator {

    /**
     * @param calc calculator, which will calculate user requests
     * @param out  output method (System.out by default in main method)
     * @param in   input method (System.in by default in main method)
     */
    public TrigonoInteractCalculator(Calculator calc, PrintStream out, InputStream in) {
        super(calc, out, in);
    }

    @Override
    protected void showHelp() {
        this.out.println("Trigonometric functions can be calculated by using following syntax: \'tan 1\'");
        super.showHelp();
    }

    private void parseArithmetic(String input) {
        super.acceptInput(input);
    }

    private void parseTrigono(String input) {
        try {
        String[] sArr = input.split(" ");
        if (sArr[1].equals("res")) {
            sArr[1] = String.valueOf(this.calc.getPrevResult());
        }
            this.showResult(((TrigonoCalculator) this.calc).calculate(Double.parseDouble(sArr[1]), sArr[0]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            out.println("Wrong values.");
        } catch (IllegalArgumentException e) {
            out.println(e.getMessage());
        }
    }

    @Override
    protected void acceptInput(String input) {
        if (input.split(" ").length != 2) {
            parseArithmetic(input);
        } else {
            parseTrigono(input);
        }
    }

    public static void main(String[] args) {
        try (TrigonoInteractCalculator i = new TrigonoInteractCalculator(new TrigonoCalculator(
                Arrays.asList(
                        new Sum(),
                        new Division(),
                        new Subtraction(),
                        new Multiplication(),
                        new Tan(),
                        new Sin(),
                        new Cos()
                )), System.out, System.in)
        ) {
            i.startMainLoop();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
