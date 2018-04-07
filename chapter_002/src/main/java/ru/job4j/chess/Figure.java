package ru.job4j.chess;

public abstract class Figure {

    final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    abstract Cell[] way(Cell source, Cell destination) throws ImpossibleMoveException;

    abstract Figure copy(Cell destination);
}
