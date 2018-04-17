package ru.job4j.chess;

/**
 * Класс, описывающий фигуру "Конь"
 */
public class Knight extends Figure {

    public Knight(Cell position) {
        super(position);
    }

    /**
     * @param source      клетка, из которой осуществляется ход
     * @param destination клетка, в которую перемещаяется фигура
     * @return массив клеток, по которым проходит фигура
     * @throws ImpossibleMoveException фигура не может совершить переход в указнную клетку
     */
    public Cell[] way(Cell source, Cell destination) throws ImpossibleMoveException {
        Cell[] way = new Cell[1];
        return way;
    }

    /**
     * Создание копии для осуществления перемещения
     *
     * @param destination клетка, в которую перемещаяется фигура
     * @return новую фигуру
     */
    public Figure copy(Cell destination) {
        return new Knight(destination);
    }
}
