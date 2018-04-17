package ru.job4j.chess;

/**
 * Класс опиcывает шахматную доску
 */
public class Board {

    private int arrayPosition = 0;
    Figure[] figures = new Figure[32];

    /**
     * Добавление новой фигуры на доску
     * @param figure фигура, которую добавляем на доску
     */
    public void add(Figure figure) {
        if (isOccupied(figure.position)) {
            throw new OccupiedCellException();
        }
        figures[arrayPosition++] = figure;
    }

    /**
     * Поиск индекса фигуры по source клетке
     * @cell клетка, на которой стоит фигура
     */
    private int indexOf(Cell cell) {
        int index = -1;
        for (int i = 0; i < figures.length; i++) {
            if (figures[i] != null) {
                if (figures[i].position.equals(cell)) {
                    index = i;
                }
            }
        }
        return index;
    }

    /**
     * Движение фигуры из одной клетки в другую
     * @param source клетка, из которой осуществляется ход
     * @param destination
     */
    public void move(Cell source, Cell destination) {
        int index = 0;
        try {
            index = checkMove(source, destination);
        } catch (ImpossibleMoveException ime) {
            System.out.println("This figure can not move to chosen cell!");
        } catch (OccupiedWayException owe) {
            System.out.println("This figure can not move through other figures!");
        } catch (FigureNotFoundException fnfe) {
            System.out.println("There is no figure on source cell!");
        }
        figures[index] = figures[index].copy(destination);
    }

    /**
     * Проверка хода, который пытается сделать фигура
     * @param source клетка, из которой осуществляется ход
     * @param destination клетка, в которую перемещаяется фигура
     * @return индекс фигуры в массиве Board.figures, которая будет перемещаться
     * @throws ImpossibleMoveException Фигура на указанной клетке не может ходить по назначенному пути
     * @throws OccupiedWayException На пути фигуры есть другая фигура (не применимо к фигуре "Конь")
     * @throws FigureNotFoundException На клетке source нет никакой фигуры
     */
    private int checkMove(Cell source, Cell destination) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        int index = indexOf(source);
        if (source == destination) {
            throw new ImpossibleMoveException();
        }
        if (index == -1) {
            throw new FigureNotFoundException();
        }
        Cell[] way = figures[index].way(source, destination);
        for (int i = 0; i < way.length - 1; i++) {
            if (!(way[i] == null)) {
                if (isOccupied(way[i])) {
                    throw new OccupiedWayException();
                }
            }
        }
        return index;
    }

    /**
     * Проверка на занятость выбранной клетки
     * @param position клетка, которая проверяется на занятость
     * @return true/false
     */
    public boolean isOccupied(Cell position) {
        boolean isOccupied = false;
        for (Figure figure : figures) {
            if (!(figure == null) && figure.position.equals(position)) {
                isOccupied = true;
            }
        }
        return isOccupied;
    }
}