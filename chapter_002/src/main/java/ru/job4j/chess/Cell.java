package ru.job4j.chess;

/**
 * @author Andrei Sigachev
 * @version $Id$
 * @since 0.1
 */
public class Cell {
    int x;
    int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Сравнение параметров двух клеток
     */
    public boolean equals(Cell cell) {
        return (this.x == cell.x && this.y == cell.y);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
