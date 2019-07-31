package ru.job4j.tictactoe;

import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isWinnerX() {
        boolean resultX = false;
        boolean resultY = false;
        for (int x = 0, y = 0; x < this.table.length; x++, y++) {
            resultX = this.fillBy(Figure3T::hasMarkX, x, 0, 0, 1);
            resultY = this.fillBy(Figure3T::hasMarkX, 0, y, 1, 0);
            if (resultX || resultY) {
                break;
            }
        }
        return resultX || resultY
                || this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 1)
                || this.fillBy(Figure3T::hasMarkX, this.table.length - 1, 0, -1, 1);
    }

    public boolean isWinnerO() {
        boolean resultX = false;
        boolean resultY = false;
        for (int x = 0, y = 0; x < this.table.length; x++, y++) {
            resultX = this.fillBy(Figure3T::hasMarkO, x, 0, 0, 1);
            resultY = this.fillBy(Figure3T::hasMarkO, 0, y, 1, 0);
            if (resultX || resultY) {
                break;
            }
        }
        return resultX || resultY
                || this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 1)
                || this.fillBy(Figure3T::hasMarkO, this.table.length - 1, 0, -1, 1);
    }

    public boolean hasGap() {
        return true;
    }
}
