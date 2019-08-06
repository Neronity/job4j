package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {
    SimpleSet<String> ss = new SimpleSet<>();

    @Test
    public void whenAddElemThenNoDuplicates() {
        int count = 0;
        ss.add("123");
        ss.add("123");
        ss.add("123");
        ss.add("123");
        for (String s : ss) {
            if (s.equals("123")) {
                count++;
            }
        }
        assertThat(count, is(1));
    }
}