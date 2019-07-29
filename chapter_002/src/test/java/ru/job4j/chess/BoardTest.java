package ru.job4j.chess;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Andrei Sigachev
 * @version $Id$
 * @since 0.1
 */
public class BoardTest {

    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Test
    public void whenBishopMovesDownRightToEmptyCell() {
        Board board = new Board();
        board.add(new Bishop(new Cell(0, 0)));
        Cell destination = new Cell(3, 3);
        board.move(new Cell(0, 0), destination);
        assertThat(board.figures[0].position, is(destination));
    }

    @Test
    public void whenBishopMovesUpLeftToEmptyCell() {
        Board board = new Board();
        board.add(new Bishop(new Cell(3, 3)));
        Cell destination = new Cell(0, 0);
        board.move(new Cell(3, 3), destination);
        assertThat(board.figures[0].position, is(destination));
    }

    @Test
    public void whenBishopMovesDownLeftToEmptyCell() {
        Board board = new Board();
        board.add(new Bishop(new Cell(6, 6)));
        Cell destination = new Cell(5, 7);
        board.move(new Cell(7, 7), destination);
        assertThat(board.figures[0].position, is(destination));
    }

    @Test
    public void whenBishopMovesUpRightToEmptyCell() {
        Board board = new Board();
        board.add(new Bishop(new Cell(5, 5)));
        Cell destination = new Cell(7, 3);
        board.move(new Cell(5, 5), destination);
        assertThat(board.figures[0].position, is(destination));
    }

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);

    }

    @Test
    public void whenBishopMovesByImpossibleWay() {
        Board board = new Board();
        board.add(new Bishop(new Cell(5, 5)));
        Cell destination = new Cell(8, 7);
        board.move(new Cell(5, 5), destination);
        assertThat(this.mem.toString(),
                is(new StringJoiner(
                        System.lineSeparator(), "",
                        System.lineSeparator())
                        .add("This figure can not move to chosen cell!").toString()
                ));

    }

    @Test
    public void whenBishopMovesByBlockedWay() {
        Board board = new Board();
        board.add(new Bishop(new Cell(5, 5)));
        board.add(new Bishop(new Cell(6, 6)));
        Cell destination = new Cell(7, 7);
        board.move(new Cell(5, 5), destination);
        assertThat(this.mem.toString(),
                is(new StringJoiner(
                        System.lineSeparator(), "",
                        System.lineSeparator())
                        .add("This figure can not move through other figures!").toString()
                ));

    }

    @Test
    public void whenMovingFigureFromTheWrongCell() {
        Board board = new Board();
        board.add(new Bishop(new Cell(5, 5)));
        Cell destination = new Cell(7, 7);
        board.move(new Cell(5, 6), destination);
        assertThat(this.mem.toString(),
                is(new StringJoiner(System.lineSeparator(), "",
                        System.lineSeparator())
                        .add("There is no figure on source cell!").toString()
                ));

    }
}
