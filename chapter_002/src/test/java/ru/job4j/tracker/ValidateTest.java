package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidateTest {

    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    @Test
    public void whenTextInsteadOfInt() {
        ValidateInput input = new ValidateInput(
                new StubInput(Arrays.asList("invalid", "6", "y"))
        );
        input.ask("Enter", Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        assertThat(
                this.mem.toString(),
                is(
                        "Введено не число! Пожалуйста, введите число!\n"
                )
        );
    }

    @Test
    public void whenOutOfMenuRange() {
        ValidateInput input = new ValidateInput(
                new StubInput(Arrays.asList("-3", "6", "y"))
        );
        input.ask("Enter", Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        assertThat(
                this.mem.toString(),
                is(
                        "Введен неверный пункт меню! Пожалуйста, введите число из диапазона меню!\n"
                )
        );
    }
}
