package ru.job4j.calculator;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrigonoInteractCalculatorTest {

    private final static String HELP = new StringJoiner(System.lineSeparator())
            .add("Trigonometric functions can be calculated by using following syntax: \'tan 1\'")
            .add("Calculation format: \'value1 operation value2\'")
            .add("Example calculation: \'4 div 2\'.")
            .add("Type \'res\' instead of value to use last calculated value.")
            .add("exit - close program")
            .add("tan - calculate tangent for given number.")
            .add("prev - last used operation.").toString();

    public String testInteraction(String input) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (TrigonoInteractCalculator ic = new TrigonoInteractCalculator(
                new TrigonoCalculator(Collections.singletonList(new Tan())),
                new PrintStream(out),
                new ByteArrayInputStream(input.getBytes()))) {
            ic.startMainLoop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    @Test
    public void whenRegularOperationThenSuccess() {
        StringJoiner expect = new StringJoiner(System.lineSeparator());
        expect.add(HELP)
                .add("Result: 1.5574077246549023")
                .add("Last used operation: tan")
                .add("");
        assertThat(testInteraction(new StringJoiner(System.lineSeparator()).add("tan 1").add("exit").toString()),
                is(expect.toString()));
    }

    @Test
    public void whenPrevResThenSuccess() {
        StringJoiner expect = new StringJoiner(System.lineSeparator());
        expect.add(HELP)
                .add("Result: 1.5574077246549023")
                .add("Last used operation: tan")
                .add("Result: 74.68593339876537")
                .add("Last used operation: tan")
                .add("");
        assertThat(testInteraction(new StringJoiner(System.lineSeparator())
                        .add("tan 1")
                        .add("tan res")
                        .add("exit").toString()),
                is(expect.toString()));
    }

    @Test
    public void whenPrevOperationThenSuccess() {
        StringJoiner expect = new StringJoiner(System.lineSeparator());
        expect.add(HELP)
                .add("Result: 1.5574077246549023")
                .add("Last used operation: tan")
                .add("Result: 1.5574077246549023")
                .add("Last used operation: tan")
                .add("");
        assertThat(testInteraction(new StringJoiner(System.lineSeparator())
                        .add("tan 1")
                        .add("prev 1")
                        .add("exit").toString()),
                is(expect.toString()));
    }

    @Test
    public void whenNoPrevValueThenErrorMessage() {
        StringJoiner expect = new StringJoiner(System.lineSeparator());
        expect.add(HELP)
                .add("There is no previous result.")
                .add("");
        assertThat(testInteraction(new StringJoiner(System.lineSeparator())
                        .add("tan res")
                        .add("exit").toString()),
                is(expect.toString()));
    }

    @Test
    public void whenNoPrevOperationThenErrorMessage() {
        StringJoiner expect = new StringJoiner(System.lineSeparator());
        expect.add(HELP)
                .add("There is no previous operation.")
                .add("");
        assertThat(testInteraction(new StringJoiner(System.lineSeparator())
                        .add("prev 1")
                        .add("exit").toString()),
                is(expect.toString()));
    }

    @Test
    public void whenInvalidCommandThenErrorMessage() {
        StringJoiner expect = new StringJoiner(System.lineSeparator());
        expect.add(HELP)
                .add("Wrong values.")
                .add("");
        assertThat(testInteraction(new StringJoiner(System.lineSeparator())
                        .add("1123456asd1")
                        .add("exit").toString()),
                is(expect.toString()));
    }
}