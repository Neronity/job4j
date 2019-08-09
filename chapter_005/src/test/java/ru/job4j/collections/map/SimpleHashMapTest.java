package ru.job4j.collections.map;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {
    SimpleHashMap<String, String> shm = new SimpleHashMap<>();

    @Test
    public void whenAddItemThenSuccess() {
        assertThat(shm.insert("1", "1"), is(true));
        assertThat(shm.insert("1", "1"), is(false));
    }

    @Test
    public void whenGetItemThenSuccess() {
        shm.insert("1", "1");
        assertThat(shm.get("1"), is("1"));
    }

    @Test
    public void whenPutALotOfItemsThenResizeSuccess() {
        shm.insert("1", "1");
        shm.insert("2", "2");
        shm.insert("3", "3");
        shm.insert("4", "4");
        shm.insert("5", "5");
        shm.insert("6", "6");
        List<String> result = Arrays.asList(
                shm.get("1"),
                shm.get("2"),
                shm.get("3"),
                shm.get("4"),
                shm.get("5"),
                shm.get("6"));
        List<String> expect = Arrays.asList("1", "2", "3", "4", "5", "6");
        assertThat(result, is(expect));
    }

    @Test
    public void whenDeletingThenSuccess() {
        shm.insert("1", "1");
        assertThat(shm.delete("1"), is(true));
        assertThat(shm.delete("1"), is(false));
        assert (shm.get("1") == null);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateThenCorrectIteration() {
        shm.insert("1", "1");
        shm.insert("2", "2");
        shm.insert("3", "3");
        shm.insert("4", "4");
        shm.insert("5", "5");
        shm.insert("6", "6");
        shm.insert("6", "6");
        Iterator<String> it = shm.iterator();
        assertThat(it.next(), is("1"));
        assertThat(it.hasNext(), is(true));
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(false));
        it.next();
    }
}