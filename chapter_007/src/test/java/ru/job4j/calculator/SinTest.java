package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SinTest {

    private Sin s = new Sin();

    @Test
    public void whenExecSinThenCorrectAnswer() {
        assertThat(s.execute(1), is(0.8414709848078965));
    }

}