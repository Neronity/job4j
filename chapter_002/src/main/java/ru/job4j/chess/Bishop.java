package ru.job4j.chess;

/**
 * Класс, описывающий фигуру "Слон"
 */
public class Bishop extends Figure {

    public Bishop(Cell position) {
        super(position);
    }

    /**
     * Движения фигуры
     *
     * @param source      клетка, из которой осуществляется ход
     * @param destination клетка, в которую перемещаяется фигура
     * @return массив клеток, по которым проходит фигура
     * @throws ImpossibleMoveException фигура не может совершить переход в указнную клетку
     */
    public Cell[] way(Cell source, Cell destination) throws ImpossibleMoveException {
        Cell[] way = new Cell[7];
        int index = 0;
        if (destination.x - source.x != destination.y - source.y) {
            throw new ImpossibleMoveException();
        }
        int deltaX = ((destination.x - source.x) / Math.abs(destination.x - source.x));
        int deltaY = ((destination.y - source.y) / Math.abs(destination.y - source.y));
        while (true) {
            source.x += deltaX;
            source.y += deltaY;
            way[index++] = new Cell(source.x, source.y);
            if (source.equals(destination)) {
                break;
            }
        }
        return way;
    }

    /**
     * Создание копии для осуществления перемещения
     *
     * @param destination клетка, в которую перемещаяется фигура
     * @return новую фигуру
     */
    public Figure copy(Cell destination) {
        return new Bishop(destination);
    }
}