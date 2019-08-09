package ru.job4j.collections.set;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Test
    public void whenThen() {
        ss.add("1");
        ss.add(null);
        ss.add("2");
        ss.add("1");
        List<String> result = new ArrayList<>();
        for (String s : ss) {
            result.add(s);
        }
        assertThat(result, is(Arrays.asList("1", null, "2")));
    }
}