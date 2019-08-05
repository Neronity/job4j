package ru.job4j.lists;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {
    SimpleQueue<Integer> sq = new SimpleQueue<>();

    @Test
    public void whenPushThenSuccess() {
        sq.push(1);
        assertThat(sq.poll(), is(1));
    }

    @Test
    public void whenPollThenFIFO() {
        sq.push(1);
        sq.push(2);
        sq.push(3);
        assertThat(sq.poll(), is(1));
        assertThat(sq.poll(), is(2));
        assertThat(sq.poll(), is(3));
    }

}