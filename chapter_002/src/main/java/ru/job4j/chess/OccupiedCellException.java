package ru.job4j.chess;

public class OccupiedCellException extends RuntimeException {

    public OccupiedCellException() {
        System.out.println("There is another figure on this cell already!");
    }
}
