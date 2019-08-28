package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SumTest {
    private Sum s = new Sum();

    @Test
    public void whenSumThenSuccess() {
        assertThat(s.execute(4, 2), is(6.0));
    }

}