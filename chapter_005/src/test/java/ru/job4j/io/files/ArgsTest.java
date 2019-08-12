package ru.job4j.io.files;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ArgsTest {

    @Test
    public void whenCreateNewArgsThenCorrectParsing() {
        Args a = new Args(new String[] {"-d", "222", "-n", "*.q", "-f", "-o", "123"});
        assertThat(a.getDirectory(), is("222"));
        assertThat(a.getFilter(), is("*.q"));
        assertThat(a.getOutput().getName(), is("123"));
        assert(a.getSearchType().getClass() == Args.FullMatchSearch.class);
    }

}