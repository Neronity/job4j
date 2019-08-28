package ru.job4j.calculator;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CalculatorTest {
    private Sum s = new Sum();
    private final Calculator calc = new Calculator(Arrays.asList(s, new Division()));

    @Test
    public void whenCalculateThenCorrectResult() {
        assertThat(this.calc.calculate(1, 2, s.getOperationKey()), is(3.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongOperationThenException() {
        this.calc.calculate(1, 2, "1111");
    }

}