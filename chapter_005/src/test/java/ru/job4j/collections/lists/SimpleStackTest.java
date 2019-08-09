package ru.job4j.collections.lists;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleStackTest {

    SimpleStack<Integer> ss = new SimpleStack<>();

    @Test
    public void whenPushThenElementAdded() {
        ss.push(34);
        assertThat(ss.poll(), is(34));
    }

    @Test
    public void whenPollThenGetLastElementAndRemoveIt() {
     ss.push(89);
     ss.push(12);
     ss.push(1);
     assertThat(ss.poll(), is(1));
     assertThat(ss.poll(), is(12));
     assertThat(ss.poll(), is(89));
     assertThat(ss.getSize(), is(0));
    }
}