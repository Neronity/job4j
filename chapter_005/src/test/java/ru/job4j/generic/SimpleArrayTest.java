package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    private SimpleArray<String> sa = new SimpleArray<>(10);

    @Test
    public void whenAddStringThenSuccess() {
        sa.add("1234");
        assertThat(sa.get(0), is("1234"));
    }

    @Test
    public void whenRemoveStringThenNoGap() {
        sa.add("1234");
        sa.add("4321");
        sa.add("543");
        sa.remove(1);
        assertThat(sa.get(1), is("543"));
    }

    @Test
    public void whenAddAfterDeletionThenCorrectElementIndex() {
        sa.add("1234");
        sa.add("12");
        sa.remove(1);
        sa.add("goop");
        assertThat(sa.get(1), is("goop"));
    }

    @Test
    public void whenSetElementThenSuccessfulUpdate() {
        sa.add("ap");
        sa.add("lk");
        sa.set(1, "kl");
        assertThat(sa.get(1), is("kl"));
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
}