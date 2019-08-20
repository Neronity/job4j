package ru.job4j.tracker;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url") + config.getProperty("data-base"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


    @Test
    public void checkConnection() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollBack.create(this.init()))) {
            assertThat(tracker.init(), is(true));
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollBack.create(this.init()))) {
            Item item = new Item("test1", "testDescription", "123L");
            tracker.add(item);
            List<Item> ls = tracker.getAll();
            assertThat(ls.get(ls.size() - 1), Is.is(item));
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollBack.create(this.init()))) {
            Item previous = new Item("test1", "testDescription", "123L");
            Item next = new Item("test2", "testDescription2", "1234L");
            tracker.add(previous);
            tracker.replace(previous.getId(), next);
            assertThat(tracker.findById(previous.getId()).getName(), Is.is("test2"));
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    public void whenDeleteThenReturnNextName() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollBack.create(this.init()))) {
            Item item1 = new Item("test1", "testDescription", "123L");
            Item item2 = new Item("test2", "testDescription2", "1234L");
            Item item3 = new Item("test3", "testDesc3", "12345L");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            int size = tracker.getAll().size();
            tracker.delete(item2.getId());
            assertThat(tracker.getAll().size(), Is.is(size - 1));
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    public void whenFindById1ThenItemWithId1() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollBack.create(this.init()))) {
            Item item1 = new Item("test1", "testDescription", "123L");
            Item item2 = new Item("test2", "testDescription2", "1234L");
            Item item3 = new Item("test3", "testDesc3", "12345L");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            assertThat(tracker.findById(item2.getId()), Is.is(item2));
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    public void whenFindByName1ThenItemWithName1() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollBack.create(this.init()))) {
            Item item1 = new Item("test1", "testDescription", "123L");
            Item item2 = new Item("test2", "testDescription2", "1234L");
            Item item3 = new Item("test3", "testDesc3", "12345L");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            assertThat(tracker.findByName(item2.getName()).get(0), Is.is(item2));
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    public void whenGetAllThreeItemsThenThreeItems() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollBack.create(this.init()))) {
            Item item1 = new Item("test1", "testDescription", "123L");
            Item item2 = new Item("test2", "testDescription2", "1234L");
            Item item3 = new Item("test3", "testDesc3", "12345L");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            assertThat(tracker.getAll(), Is.is(Arrays.asList(item1, item2, item3)));
        } catch (SQLException e) {
            fail();
        }
    }

}