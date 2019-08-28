package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DivisionTest {
    private Division d = new Division();

    @Test
    public void whenDivideThenSuccess() {
        assertThat(d.execute(4, 2), is(2.0));
    }

}