package ru.job4j.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayTask {
    private Integer[] array;

    public List<Integer> evenList() {
        return Arrays.stream(array).filter(i -> i % 2 == 0).collect(Collectors.toList());
    }

    public List<Integer> arrayToPow() {
        return Arrays.stream(array).map(i -> i * i).collect(Collectors.toList());
    }

    public int arraySum() {
        return Arrays.stream(array).reduce((acc, i) -> acc += i).orElse(-1);
    }

    public void setArray(Integer[] array) {
        this.array = array;
    }

}
