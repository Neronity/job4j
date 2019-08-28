package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CosTest {
    private Cos c = new Cos();

    @Test
    public void whenExecCosThenCorrectAnswer() {
        assertThat(c.execute(1), is(0.5403023058681398));
    }

}