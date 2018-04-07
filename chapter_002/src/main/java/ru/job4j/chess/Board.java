package ru.job4j.chess;

public class Board {

    private int arrayPosition = 0;
    Figure[] figures = new Figure[32];

    public void add(Figure figure) {
        if (isOccupied(figure.position)) {
            throw new OccupiedCellException();
        }
        figures[arrayPosition++] = figure;
    }

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

    public boolean isOccupied(Cell position) {
        boolean isOccupied = false;
        for (Figure figure : figures) {
            if (!(figure == null) && figure.position.x == position.x && figure.position.y == position.y) {
                isOccupied = true;
            }
        }
        return isOccupied;
    }

    public static class Bishop extends Figure {

        public Bishop(Cell position) {
            super(position);
        }

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

        public Figure copy(Cell destination) {
            return new Bishop(destination);
        }
    }

    public class Knight extends Figure {

        public Knight(Cell position) {
            super(position);
        }

        public Cell[] way(Cell source, Cell destination) throws ImpossibleMoveException {
            return new Cell[40];
        }

        public Figure copy(Cell destination) {
            return new Knight(destination);
        }
    }
}
