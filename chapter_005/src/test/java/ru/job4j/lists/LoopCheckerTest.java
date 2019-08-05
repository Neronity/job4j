package ru.job4j.lists;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LoopCheckerTest {

    private LoopChecker lc = new LoopChecker();
    private LoopChecker.Node<Integer> first = new LoopChecker.Node<>(1);
    private LoopChecker.Node<Integer> second = new LoopChecker.Node<>(2);
    private LoopChecker.Node<Integer> third = new LoopChecker.Node<>(3);
    private LoopChecker.Node<Integer> fourth = new LoopChecker.Node<>(4);

    @Test
    public void whenHasLoopThenTrue() {
        first.next = second;
        second.next = third;
        third.next = second;
        assertThat(lc.hasLoop(first), is(true));
    }

    @Test
    public void whenHasNoLoopThenFalse() {
        first.next = second;
        second.next = third;
        third.next = fourth;
        assertThat(lc.hasLoop(first), is(false));
    }
}