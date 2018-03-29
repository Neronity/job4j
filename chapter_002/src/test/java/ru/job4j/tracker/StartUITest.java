package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

	@Test
	public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
   		Tracker tracker = new Tracker();     
   		Input input = new StubInput(new String[]{"0", "test name", "desc", "6"}); 
   		new StartUI(input, tracker).init();
   		assertThat(tracker.getAll()[0].getName(), is("test name"));
	}

 	@Test
 	public void whenReplaceThenTrackerHasSameItemList() {
    	Tracker tracker = new Tracker();
    	Item item = new Item("test", "test desc", null);
    	String id = tracker.add(item);
    	Input input = new StubInput(new String[]{"1", id, "test name", "desc", "6"});
    	new StartUI(input, tracker).init();
    	assertThat(tracker.getAll()[0].getId(), is(id));
 	}

 	@Test
 	public void whenDeleteItemThenTrackerHasRemainingItems() {
 		Tracker tracker = new Tracker();
    	Item item = new Item("test", "test desc", null);
		String id = tracker.add(item);
		Item item1 = new Item("test1", "test1 desc", null);
		tracker.add(item1);
    	Input input = new StubInput(new String[]{"2", id, "6"});
    	new StartUI(input, tracker).init();
    	assertThat(tracker.getAll()[0], is(item1));

 	}

 	@Test
 	public void whenFindingByIdThenCorrectItem() {
 		Tracker tracker = new Tracker();
    	Item item = new Item("test", "test desc", null);
    	tracker.add(item);
    	Item item1 = new Item("test1", "test1 desc", null);
    	String id = tracker.add(item1);
    	Input input = new StubInput(new String[]{"3", id, "6"});
    	new StartUI(input, tracker).init();
    	assertThat(tracker.getAll()[1].getId(), is(id));
 	}

 	@Test
 	public void whenFindingByNameThenCorrectItem() {
 		Tracker tracker = new Tracker();
    	Item item = new Item("test", "test desc", null);
    	tracker.add(item);
    	Item item1 = new Item("test1", "test1 desc", null);
    	String id = tracker.add(item1);
    	Input input = new StubInput(new String[]{"4", item.getName(), "6"});
    	assertThat(tracker.getAll()[1].getId(), is(id));
 	}
}