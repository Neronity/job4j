package ru.job4j.streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ArrayTaskTest {
    ArrayTask at = new ArrayTask();

    @Test
    public void whenListThenEvenList() {
        at.setArray(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        List<Integer> result = at.evenList();
        List<Integer> expect = Arrays.asList(2, 4, 6, 8, 10);
        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayThenPoweredArray() {
        at.setArray(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        List<Integer> result = at.arrayToPow();
        List<Integer> expect = Arrays.asList(1, 4, 9, 16, 25, 36, 49, 64, 81, 100);
        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayThenSumOfElements() {
        at.setArray(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        int result = at.arraySum();
        int expect = 55;
        assertThat(result, is(expect));
    }
}