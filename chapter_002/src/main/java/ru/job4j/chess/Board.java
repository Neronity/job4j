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
     * Движение фигуры из одной клетки в другую
     * @param source клетка, из которой осуществляется ход
     * @param destination
     */
    public void move(Cell source, Cell destination) {
        Figure newFigure = null;
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
        for (int i = index; i < arrayPosition; i++) {
            if (i == arrayPosition - 1) {
                newFigure = figures[i].copy(destination);
                figures[i] = null;
                arrayPosition -= 1;
                break;
            } else {
                figures[i] = figures[i + 1];
            }
        }
        add(newFigure);

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
        Figure movingFigure = null;
        int index = -1;
        for (int i = 0; i < arrayPosition; i++) {
            if (figures[i].position.x == source.x && figures[i].position.y == source.y) {
                movingFigure = figures[i];
                index = i;
                break;
            }
        }
        if (movingFigure == null) {
            throw new FigureNotFoundException();
        }
        Cell[] way = movingFigure.way(source, destination);
        if (!(movingFigure instanceof Knight)) {
            for (int i = 1; i < way.length - 1; i++) {
                if (!(way[i] == null)) {
                    if (isOccupied(way[i])) {
                        throw new OccupiedWayException();
                    }
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
            if (!(figure == null) && figure.position.x == position.x && figure.position.y == position.y) {
                isOccupied = true;
            }
        }
        return isOccupied;
    }

    /**
     * Класс, описывающий фигуру "Слон"
     */
    public static class Bishop extends Figure {

        public Bishop(Cell position) {
            super(position);
        }

        /**
         * Движения фигуры
         * @param source клетка, из которой осуществляется ход
         * @param destination клетка, в которую перемещаяется фигура
         * @return массив клеток, по которым проходит фигура
         * @throws ImpossibleMoveException фигура не может совершить переход в указнную клетку
         */
        public Cell[] way(Cell source, Cell destination) throws ImpossibleMoveException {
            Cell[] way = new Cell[40];
            int index = 0;
            if (destination.x > source.x && destination.y > source.y) {
                while (source.x <= destination.x && source.y <= destination.y) {
                    way[index++] = new Cell(source.x, source.y);
                    source.x++;
                    source.y++;
                }
            } else if (destination.x < source.x && destination.y < source.y) {
                while (source.x >= destination.x && source.y >= destination.y) {
                    way[index++] =  new Cell(source.x, source.y);
                    source.x--;
                    source.y--;
                }
            } else if (destination.x < source.x && destination.y > source.y) {
                while (source.x >= destination.x && source.y <= destination.y) {
                    way[index++] =  new Cell(source.x, source.y);
                    source.x--;
                    source.y++;
                }
            } else if (destination.x > source.x && destination.y < source.y) {
                while (source.x <= destination.x && source.y >= destination.y) {
                    way[index++] =  new Cell(source.x, source.y);
                    source.x++;
                    source.y--;
                }
            }
            if (!(way[index - 1].x == destination.x && way[index - 1].y == destination.y)) {
                throw new ImpossibleMoveException();
            }
            return way;
        }

        /**
         * Создание копии для осуществления перемещения
         * @param destination клетка, в которую перемещаяется фигура
         * @return новую фигуру
         */
        public Figure copy(Cell destination) {
            return new Bishop(destination);
        }
    }

    /**
     * Класс, описывающий фигуру "Конь"
     */
    public class Knight extends Figure {

        public Knight(Cell position) {
            super(position);
        }

        /**
         *
         * @param source клетка, из которой осуществляется ход
         * @param destination клетка, в которую перемещаяется фигура
         * @return массив клеток, по которым проходит фигура
         * @throws ImpossibleMoveException фигура не может совершить переход в указнную клетку
         */
        public Cell[] way(Cell source, Cell destination) throws ImpossibleMoveException {
            return new Cell[40];
        }

        /**
         * Создание копии для осуществления перемещения
         * @param destination клетка, в которую перемещаяется фигура
         * @return новую фигуру
         */
        public Figure copy(Cell destination) {
            return new Knight(destination);
        }
    }
}
