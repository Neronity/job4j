package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SubtractionTest {
    private Subtraction s = new Subtraction();

    @Test
    public void whenSubtractThenSuccess() {
        assertThat(s.execute(4, 2), is(2.0));
    }

}