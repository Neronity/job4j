package ru.job4j.collections.lists;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleLinkedListTest {

    private SimpleLinkedList<Integer> ll = new SimpleLinkedList<>();

    @Test
    public void whenAddStringThenSuccess() {
        ll.add(1234);
        assertThat(ll.get(0), is(1234));
    }

    @Test
    public void whenGetIteratorThenSuccessfulIterate() {
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);
        Iterator<Integer> it = ll.iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        assertThat(sb.toString(), is("54321"));
    }

    @Test
    public void whenHasNextThenCorrectResponse() {
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);
        Iterator<Integer> it = ll.iterator();
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
            ll.add(i);
        }
        Iterator<Integer> it = ll.iterator();
        ll.add(11);
        it.next();
    }

    @Test
    public void whenAdding100ElementsThenNoExceptions() {
        for (int i = 0; i < 100; i++) {
            ll.add(i);
        }
    }

    @Test
    public void whenRemoveFirstThenSuccess() {
        ll.add(1);
        ll.removeFirst();
        assertThat(ll.getSize(), is(0));
    }
}