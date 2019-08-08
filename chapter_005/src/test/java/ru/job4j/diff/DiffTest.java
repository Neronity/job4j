package ru.job4j.diff;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DiffTest {

    @Test
    public void whenCalculateDiffThenCorrectInfo() {
        Diff d = new Diff();
        List<Diff.User> previous = new ArrayList<>();
        previous.add(new Diff.User(1, "11"));
        previous.add(new Diff.User(2, "22"));
        previous.add(new Diff.User(3, "33"));
        previous.add(new Diff.User(4, "44"));
        List<Diff.User> current = new ArrayList<>();
        current.add(new Diff.User(1, "12"));
        current.add(new Diff.User(3, "33"));
        current.add(new Diff.User(4, "44"));
        current.add(new Diff.User(5, "55"));
        Diff.Info result = d.diff(previous, current);
        assertThat(result.added, is(1));
        assertThat(result.changed, is(1));
        assertThat(result.deleted, is(1));
    }

}