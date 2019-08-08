package ru.job4j.tree;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleTreeClassTest {

    @Test
    public void when6ElFindLastThen6() {
        SimpleTreeClass<Integer> tree = new SimpleTreeClass<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        SimpleTreeClass<Integer> tree = new SimpleTreeClass<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenIterateOverThenSuccess() {
        SimpleTreeClass<Integer> tree = new SimpleTreeClass<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        Iterator<Integer> itr = tree.iterator();
        assertThat(itr.hasNext(), is(true));
        itr.next();
        itr.next();
        itr.next();
        assertThat(itr.next(), is(4));
        itr.next();
        itr.next();
        assertThat(itr.hasNext(), is(false));
    }
}