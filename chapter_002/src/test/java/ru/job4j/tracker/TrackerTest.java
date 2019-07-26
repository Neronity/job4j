package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {

	@Test
	public void whenAddNewItemThenTrackerHasSameItem() {
 		Tracker tracker = new Tracker();
 		Item item = new Item("test1", "testDescription", "123L");
 		tracker.add(item);
 		assertThat(tracker.getAll().get(0), is(item));
	}

	@Test
	public void whenReplaceNameThenReturnNewName() {
    	Tracker tracker = new Tracker();
    	Item previous = new Item("test1", "testDescription", "123L");
    	tracker.add(previous);
    	Item next = new Item("test2", "testDescription2", "1234L");
    	next.setId(previous.getId());
    	tracker.replace(previous.getId(), next);
    	assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
	}

	@Test
	public void whenDeleteThenReturnNextName() {
		Tracker tracker = new Tracker();
    	Item item1 = new Item("test1", "testDescription", "123L");
    	tracker.add(item1);
    	Item item2 = new Item("test2", "testDescription2", "1234L");
    	tracker.add(item2);
    	Item item3 = new Item("test3", "testDesc3", "12345L");
    	tracker.add(item3);
    	tracker.delete(item2.getId());
    	assertThat(tracker.getAll().size(), is(2));
    }

    @Test
    public void whenFindById1ThenItemWithId1() {
    	Tracker tracker = new Tracker();
    	Item item1 = new Item("test1", "testDescription", "123L");
    	tracker.add(item1);
    	Item item2 = new Item("test2", "testDescription2", "1234L");
    	tracker.add(item2);
    	Item item3 = new Item("test3", "testDesc3", "12345L");
    	tracker.add(item3);
    	assertThat(tracker.findById(item2.getId()), is(item2));
    }

    @Test
    public void whenFindByName1ThenItemWithName1() {
    	Tracker tracker = new Tracker();
    	Item item1 = new Item("test1", "testDescription", "123L");
    	tracker.add(item1);
    	Item item2 = new Item("test2", "testDescription2", "1234L");
    	tracker.add(item2);
    	Item item3 = new Item("test3", "testDesc3", "12345L");
    	tracker.add(item3);
    	assertThat(tracker.findByName(item2.getName()), is(item2));
    }

    @Test
    public void whenGetAllThreeItemsThenThreeItems() {
    	Tracker tracker = new Tracker();
    	Item item1 = new Item("test1", "testDescription", "123L");
    	tracker.add(item1);
    	Item item2 = new Item("test2", "testDescription2", "1234L");
    	tracker.add(item2);
    	Item item3 = new Item("test3", "testDesc3", "12345L");
    	tracker.add(item3);
    	assertThat(tracker.getAll(), is(Arrays.asList(item1, item2, item3)));
    }

    @Test
    public void whenItem2IndexThen1() {
    	Tracker tracker = new Tracker();
    	Item item1 = new Item("test1", "testDescription", "123L");
    	tracker.add(item1);
    	Item item2 = new Item("test2", "testDescription2", "1234L");
    	tracker.add(item2);
    	Item item3 = new Item("test3", "testDesc3", "12345L");
    	tracker.add(item3);
    	assertThat(tracker.getItemIndex(item2.getId()), is(1));
    }
}