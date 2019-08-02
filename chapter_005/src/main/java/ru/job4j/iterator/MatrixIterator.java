package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;

public class MatrixIterator implements Iterator {
    private int[][] matrix;
    private int indexX = 0;
    private int indexY = 0;

    public MatrixIterator(int[][] matrix) {
        this.matrix = matrix;
    }


    @Override
    public boolean hasNext() {
        return indexY < matrix.length && (indexX < matrix[indexY].length || indexY + 1 < matrix.length);
    }

    @Override
    public Object next() {
        int result = matrix[indexY][indexX];
        indexX = indexX == matrix[indexY].length - 1 ? 0 : ++indexX;
        indexY = indexX == 0 ? ++indexY : indexY;
        return result;
    }
}
