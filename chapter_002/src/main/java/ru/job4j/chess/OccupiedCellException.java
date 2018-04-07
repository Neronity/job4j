package ru.job4j.chess;

/**
 * @author Andrei Sigachev
 * @version $Id$
 * @since 0.1
 */
public class OccupiedCellException extends RuntimeException {

    public OccupiedCellException() {
        System.out.println("There is another figure on this cell already!");
    }
}
