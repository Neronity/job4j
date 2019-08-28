package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MultiplicationTest {

    private Multiplication m = new Multiplication();

    @Test
    public void whenMultThenSuccess() {
        assertThat(m.execute(4, 2), is(8.0));
    }

}