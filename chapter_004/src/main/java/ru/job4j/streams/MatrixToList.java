package ru.job4j.streams;

import java.util.List;
import java.util.stream.Collectors;

public class MatrixToList {

    public List<Integer> matrixToList(Integer[][] matrix) {
        return List.of(matrix).stream().flatMap(e -> List.of(e).stream()).collect(Collectors.toList());
    }
}
