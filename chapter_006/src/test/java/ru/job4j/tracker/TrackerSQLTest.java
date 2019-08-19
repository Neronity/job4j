package ru.job4j.tracker;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {

    private final TrackerSQL sql = new TrackerSQL("test.properties");


    @After
    public void tearDown() {
        sql.resetDb();
    }

    @Test
    public void checkConnection() {
        assertThat(sql.init("test.properties"), is(true));
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Item item = new Item("test1", "testDescription", "123L");
        sql.add(item);
        List<Item> ls = sql.getAll();
        assertThat(ls.get(ls.size() - 1), Is.is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Item previous = new Item("test1", "testDescription", "123L");
        Item next = new Item("test2", "testDescription2", "1234L");
        sql.add(previous);
        sql.replace(previous.getId(), next);
        assertThat(sql.findById(previous.getId()).getName(), Is.is("test2"));
    }

    @Test
    public void whenDeleteThenReturnNextName() {
        Item item1 = new Item("test1", "testDescription", "123L");
        Item item2 = new Item("test2", "testDescription2", "1234L");
        Item item3 = new Item("test3", "testDesc3", "12345L");
        sql.add(item1);
        sql.add(item2);
        sql.add(item3);
        int size = sql.getAll().size();
        sql.delete(item2.getId());
        assertThat(sql.getAll().size(), Is.is(size - 1));
    }

    @Test
    public void whenFindById1ThenItemWithId1() {
        Item item1 = new Item("test1", "testDescription", "123L");
        Item item2 = new Item("test2", "testDescription2", "1234L");
        Item item3 = new Item("test3", "testDesc3", "12345L");
        sql.add(item1);
        sql.add(item2);
        sql.add(item3);
        assertThat(sql.findById(item2.getId()), Is.is(item2));
    }

    @Test
    public void whenFindByName1ThenItemWithName1() {
        Item item1 = new Item("test1", "testDescription", "123L");
        Item item2 = new Item("test2", "testDescription2", "1234L");
        Item item3 = new Item("test3", "testDesc3", "12345L");
        sql.add(item1);
        sql.add(item2);
        sql.add(item3);
        assertThat(sql.findByName(item2.getName()).get(0), Is.is(item2));
    }

    @Test
    public void whenGetAllThreeItemsThenThreeItems() {
        Item item1 = new Item("test1", "testDescription", "123L");
        Item item2 = new Item("test2", "testDescription2", "1234L");
        Item item3 = new Item("test3", "testDesc3", "12345L");
        sql.add(item1);
        sql.add(item2);
        sql.add(item3);
        assertThat(sql.getAll(), Is.is(Arrays.asList(item1, item2, item3)));
    }

}