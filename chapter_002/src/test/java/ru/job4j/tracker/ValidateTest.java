package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
                new StubInput(new String[] {"invalid", "6", "y"})
        );
        input.ask("Enter", new int[] {0, 1, 2, 3, 4, 5, 6});
        assertThat(
                this.mem.toString(),
                is(
                        "Введено не число! Пожалуйста, введите число!\r\n"
                )
        );
    }

    @Test
    public void whenOutOfMenuRange() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"-3", "6", "y"})
        );
        input.ask("Enter", new int[] {0, 1, 2, 3, 4, 5, 6});
        assertThat(
                this.mem.toString(),
                is(
                        "Введен неверный пункт меню! Пожалуйста, введите число из диапазона меню!\r\n"
                )
        );
    }
}
