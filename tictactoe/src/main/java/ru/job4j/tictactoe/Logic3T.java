package ru.job4j.tictactoe;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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
        return isWinner(Figure3T::hasMarkX);
    }

    public boolean isWinnerO() {
        return isWinner(Figure3T::hasMarkO);
    }

    private boolean isWinner(Predicate<Figure3T> p) {
        boolean resultX = false;
        boolean resultY = false;
        for (int x = 0, y = 0; x < this.table.length; x++, y++) {
            resultX = this.fillBy(p, x, 0, 0, 1);
            resultY = this.fillBy(p, 0, y, 1, 0);
            if (resultX || resultY) {
                break;
            }
        }
        return resultX || resultY
                || this.fillBy(p, 0, 0, 1, 1)
                || this.fillBy(p, this.table.length - 1, 0, -1, 1);
    }

    public boolean hasGap() {
        return Arrays.stream(table)
                .flatMap(Arrays::stream)
                .anyMatch(e -> !e.hasMarkX() && !e.hasMarkO());
    }
}
