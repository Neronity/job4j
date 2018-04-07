package ru.job4j.chess;

/**
 * @author Andrei Sigachev
 * @version $Id$
 * @since 0.1
 */
public abstract class Figure {

    final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    abstract Cell[] way(Cell source, Cell destination) throws ImpossibleMoveException;

    abstract Figure copy(Cell destination);
}
