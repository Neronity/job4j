package ru.job4j.io.zip;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ArgsTest {

    @Test
    public void whenCreateNewArgsThenCorrectParsing() {
        Args a = new Args(new String[] {"-d", "222", "-e", "*.q", "*.w", "-o", "123"});
        assertThat(a.getDirectory(), is("222"));
        assertThat(a.getExclude(), is(Arrays.asList("q", "w")));
        assertThat(a.getOutput().getName(), is("123"));
    }
}