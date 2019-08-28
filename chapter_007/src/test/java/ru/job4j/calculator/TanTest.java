package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TanTest {
    private Tan t = new Tan();

    @Test
    public void whenExecSinThenCorrectAnswer() {
        assertThat(t.execute(1), is(1.5574077246549023));
    }
}