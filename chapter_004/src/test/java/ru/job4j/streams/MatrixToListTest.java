package ru.job4j.streams;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixToListTest {
    MatrixToList mx = new MatrixToList();

    @Test
    public void whenMatrixThenListOfInt() {
        List<Integer> result = mx.matrixToList(new Integer[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9, 10}});
        List<Integer> expect = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertThat(result, is(expect));
    }
}
