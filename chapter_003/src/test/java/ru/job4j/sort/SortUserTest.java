package ru.job4j.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SortUserTest {

    @Test
    public void whenUserListThenSortedUserSet() {
        User u1 = new User(4, "ivan");
        User u2 = new User(2, "viktor");
        User u3 = new User(3, "artem");
        User u4 = new User(1, "nikolay");
        List<User> list = new ArrayList<>(Arrays.asList(
                u1,
                u2,
                u3,
                u4
        ));

        List<User> result = new ArrayList<>(new SortUser().sort(list));
        List<User> expect = new ArrayList<>(
                Arrays.asList(
                        u4,
                        u2,
                        u3,
                        u1
                ));
        assertThat(result, is(expect));
    }

    @Test
    public void whenSortByNameLengthThenSortedList() {
        User u1 = new User(4, "Ирa");
        User u2 = new User(2, "Ирина");
        User u3 = new User(3, "Филипп");
        User u4 = new User(1, "Добромир");
        List<User> list = new ArrayList<>(Arrays.asList(
                u4,
                u3,
                u2,
                u1
        ));
        List<User> result = new SortUser().sortNameLength(list);
        List<User> expect = new ArrayList<>(
                Arrays.asList(
                        u1,
                        u2,
                        u3,
                        u4
                )
        );
        assertThat(result, is(expect));
    }


    @Test
    public void whenSameNameThenSortByAge() {
        User u1 = new User(4, "Ирина");
        User u2 = new User(2, "Ирина");
        User u3 = new User(3, "Филипп");
        User u4 = new User(1, "Филипп");
        List<User> list = new ArrayList<>(Arrays.asList(
                u1,
                u3,
                u2,
                u4
        ));

        List<User> result = new SortUser().sortByAllFields(list);
        List<User> expect = new ArrayList<>(
                Arrays.asList(
                        u2,
                        u1,
                        u4,
                        u3
                )
        );
        assertThat(result, is(expect));
    }
}
