package ru.job4j.collections.lists;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleDynamicListTest {

    private SimpleDynamicList<String> sa = new SimpleDynamicList<>();

    @Test
    public void whenAddStringThenSuccess() {
        sa.add("1234");
        assertThat(sa.get(0), is("1234"));
    }

    @Test
    public void whenGetIteratorThenSuccessfulIterate() {
        sa.add("1");
        sa.add("2");
        sa.add("3");
        sa.add("4");
        sa.add("5");
        Iterator<String> it = sa.iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        assertThat(sb.toString(), is("12345"));
    }

    @Test
    public void whenHasNextThenCorrectResponse() {
        sa.add("1");
        sa.add("2");
        sa.add("3");
        sa.add("4");
        sa.add("5");
        Iterator<String> it = sa.iterator();
        assertThat(it.hasNext(), is(true));
        it.next();
        it.next();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurrentModThenException() {
        for (int i = 0; i < 10; i++) {
            sa.add(String.valueOf(i));
        }
        Iterator<String> it = sa.iterator();
        sa.add("11");
        it.next();
    }

    @Test
    public void whenAdding100ElementsThenNoExceptions() {
        for (int i = 0; i < 100; i++) {
            sa.add(String.valueOf(i));
        }
    }
}