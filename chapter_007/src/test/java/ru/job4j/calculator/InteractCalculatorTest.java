package ru.job4j.calculator;

import org.junit.Test;

import java.io.*;
import java.util.Collections;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class InteractCalculatorTest {

    private final static String HELP = new StringJoiner(System.lineSeparator())
            .add("Calculation format: \'value1 operation value2\'")
            .add("Example calculation: \'4 div 2\'.")
            .add("Type \'res\' instead of value to use last calculated value.")
            .add("exit - close program")
            .add("prev - last used operation.")
            .add("+ - addition operation.").toString();

    public String testInteraction(String input) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (InteractCalculator ic = new InteractCalculator(
                new Calculator(Collections.singletonList(new Sum())),
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
                .add("Result: 2.0")
                .add("Last used operation: +")
                .add("");
        assertThat(testInteraction(new StringJoiner(System.lineSeparator()).add("1 + 1").add("exit").toString()),
                is(expect.toString()));
    }

    @Test
    public void whenPrevResThenSuccess() {
        StringJoiner expect = new StringJoiner(System.lineSeparator());
        expect.add(HELP)
                .add("Result: 2.0")
                .add("Last used operation: +")
                .add("Result: 4.0")
                .add("Last used operation: +")
                .add("");
        assertThat(testInteraction(new StringJoiner(System.lineSeparator())
                        .add("1 + 1")
                        .add("res + res")
                        .add("exit").toString()),
                is(expect.toString()));
    }

    @Test
    public void whenPrevOperationThenSuccess() {
        StringJoiner expect = new StringJoiner(System.lineSeparator());
        expect.add(HELP)
                .add("Result: 2.0")
                .add("Last used operation: +")
                .add("Result: 4.0")
                .add("Last used operation: +")
                .add("");
        assertThat(testInteraction(new StringJoiner(System.lineSeparator())
                        .add("1 + 1")
                        .add("2 prev 2")
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
                        .add("res + res")
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
                        .add("1 prev 1")
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