package ru.job4j.calculator;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class PreviousOperationTest {

    private PreviousOperation p = new PreviousOperation();

    @Test
    public void whenDivideThenSuccess() {
        p.setPrevOperation(new Sum());
        assertThat(p.execute(4, 2), is(6.0));
    }

    @Before
    public void before() {
        p.setPrevOperation(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoOperationThenException() {
        p.execute(1, 2);
    }

}