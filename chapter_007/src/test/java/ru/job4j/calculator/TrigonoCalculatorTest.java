package ru.job4j.calculator;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrigonoCalculatorTest {
    private Tan t = new Tan();
    private final TrigonoCalculator calc = new TrigonoCalculator(Arrays.asList(t, new Sin()));

    @Test
    public void whenCalculateThenCorrectResult() {
        assertThat(this.calc.calculate(1, t.getOperationKey()), is(1.5574077246549023));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongOperationThenException() {
        this.calc.calculate(1, "1111");
    }

}