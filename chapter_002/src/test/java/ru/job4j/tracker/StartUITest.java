package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

	@Before
	public void loadOutput() {
		System.setOut(new PrintStream(this.out));
	}

	@After
	public void backOutput() {
		System.setOut(this.stdout);
	}

	private final PrintStream stdout = System.out;
	private final ByteArrayOutputStream out = new ByteArrayOutputStream();

	@Test
	public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
   		Tracker tracker = new Tracker();     
   		Input input = new StubInput(new String[]{"0", "test name", "desc", "6", "y"});
   		new StartUI(input, tracker).init();
   		assertThat(tracker.getAll()[0].getName(), is("test name"));
	}

 	@Test
 	public void whenReplaceThenTrackerHasSameItemList() {
    	Tracker tracker = new Tracker();
    	Item item = new Item("test", "test desc", null);
    	String id = tracker.add(item);
    	Input input = new StubInput(new String[]{"1", id, "test name", "desc", "6", "y"});
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
    	Input input = new StubInput(new String[]{"2", id, "6", "y"});
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
    	Input input = new StubInput(new String[]{"3", id, "6", "y"});
    	new StartUI(input, tracker).init();
    	assertThat(tracker.getAll()[1].getId(), is(id));
 	}

 	@Test
 	public void whenFindingByNameThenCorrectItem() {
 		Tracker tracker = new Tracker();
    	Item item = new Item("test", "test desc", null);
    	tracker.add(item);
    	Item item1 = new Item("test1", "test1 desc", null);
    	tracker.add(item1);
    	Input input = new StubInput(new String[]{"4", item.getName(), "6", "y"});
		new StartUI(input, tracker).init();
    	assertThat(tracker.getAll()[0].getName(), is("test"));
 	}

	@Test
	public void whenShowMenuThenMenu() {
		new StartUI(new StubInput(new String[] {"6", "y"}), new Tracker()).init();
		assertThat(new String(out.toByteArray()),
				is(new StringBuilder()
				.append("0. Создать заявку\r\n")
				.append("1. Заменить заявку\r\n")
				.append("2. Удалить заявку\r\n")
				.append("3. Поиск по ID заявки\r\n")
				.append("4. Поиск по имени заявки\r\n")
				.append("5. Список всех заявок\r\n")
				.append("6. Выйти\r\n")
				.toString()
				)
		);
	}

	@Test
	public void whenCreateItemThenText() {
		Tracker tracker = new Tracker();
		Input input = new StubInput(new String[]{"0", "test name", "desc", "6", "y"});
		new StartUI(input, tracker).init();
		assertThat(new String(this.out.toByteArray()),
				is(
						new StringBuilder()
								.append("0. Создать заявку\r\n")
								.append("1. Заменить заявку\r\n")
								.append("2. Удалить заявку\r\n")
								.append("3. Поиск по ID заявки\r\n")
								.append("4. Поиск по имени заявки\r\n")
								.append("5. Список всех заявок\r\n")
								.append("6. Выйти\r\n")
								.append("------------ Добавление новой заявки --------------\r\n")
								.append("------------ Новая заявка с ID : " + tracker.items[0].getId() + "-----------\n\r\n")
								.toString()
				)
		);
	}

	@Test
	public void whenReplaceItemThenText() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("test", "desc", null));
		Input input = new StubInput(new String[]{"1", tracker.items[0].getId(), "test name", "desc", "6", "y"});
		new StartUI(input, tracker).init();
		assertThat(new String(this.out.toByteArray()),
				is(
						new StringBuilder()
								.append("0. Создать заявку\r\n")
								.append("1. Заменить заявку\r\n")
								.append("2. Удалить заявку\r\n")
								.append("3. Поиск по ID заявки\r\n")
								.append("4. Поиск по имени заявки\r\n")
								.append("5. Список всех заявок\r\n")
								.append("6. Выйти\r\n")
								.append("------------ Замена существующей заявки --------------\r\n")
								.append("------------ Заявка успешно заменена -----------\n\r\n")
								.toString()
				)
		);
	}

	@Test
	public void whenDeleteItemThenText() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("test", "desc", null));
		Input input = new StubInput(new String[]{"2", tracker.items[0].getId(), "6", "y"});
		new StartUI(input, tracker).init();
		assertThat(new String(this.out.toByteArray()),
				is(
						new StringBuilder()
								.append("0. Создать заявку\r\n")
								.append("1. Заменить заявку\r\n")
								.append("2. Удалить заявку\r\n")
								.append("3. Поиск по ID заявки\r\n")
								.append("4. Поиск по имени заявки\r\n")
								.append("5. Список всех заявок\r\n")
								.append("6. Выйти\r\n")
								.append("------------ Удаление существующей заявки --------------\r\n")
								.append("------------ Заявка успешно удалена -----------\n\r\n")
								.toString()
				)
		);
	}

	@Test
	public void whenFindingItemThenCorrectInfo() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("test", "desc", null));
		Input input = new StubInput(new String[]{"3", tracker.items[0].getId(), "6", "y"});
		new StartUI(input, tracker).init();
		assertThat(new String(this.out.toByteArray()),
				is(
						new StringBuilder()
								.append("0. Создать заявку\r\n")
								.append("1. Заменить заявку\r\n")
								.append("2. Удалить заявку\r\n")
								.append("3. Поиск по ID заявки\r\n")
								.append("4. Поиск по имени заявки\r\n")
								.append("5. Список всех заявок\r\n")
								.append("6. Выйти\r\n")
								.append("------------ Поиск заявки по ID --------------\r\n")
								.append("------------ Имя заявки --------------\n" + tracker.items[0].getName() + "\r\n")
								.append("------------ Описание заявки --------------\n" + tracker.items[0].getDesc() + "\r\n")
								.append("Уникальный ID: " + tracker.items[0].getId() + "\n\r\n")
								.toString()
				)
		);
	}

	@Test
	public void whenFindingItemByNameThenCorrectInfo() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("test", "desc", null));
		Input input = new StubInput(new String[]{"4", tracker.items[0].getName(), "6", "y"});
		new StartUI(input, tracker).init();
		assertThat(new String(this.out.toByteArray()),
				is(
						new StringBuilder()
								.append("0. Создать заявку\r\n")
								.append("1. Заменить заявку\r\n")
								.append("2. Удалить заявку\r\n")
								.append("3. Поиск по ID заявки\r\n")
								.append("4. Поиск по имени заявки\r\n")
								.append("5. Список всех заявок\r\n")
								.append("6. Выйти\r\n")
								.append("------------ Поиск заявки по имени --------------\r\n")
								.append("------------ Имя заявки --------------\n" + tracker.items[0].getName() + "\r\n")
								.append("------------ Описание заявки --------------\n" + tracker.items[0].getDesc() + "\r\n")
								.append("Уникальный ID: " + tracker.items[0].getId() + "\n\r\n")
								.toString()
				)
		);
	}

	@Test
	public void whenGetListOfItemsThenCorrectInfo() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("test", "desc", null));
		Input input = new StubInput(new String[]{"5", "6", "y"});
		new StartUI(input, tracker).init();
		assertThat(new String(this.out.toByteArray()),
				is(
						new StringBuilder()
								.append("0. Создать заявку\r\n")
								.append("1. Заменить заявку\r\n")
								.append("2. Удалить заявку\r\n")
								.append("3. Поиск по ID заявки\r\n")
								.append("4. Поиск по имени заявки\r\n")
								.append("5. Список всех заявок\r\n")
								.append("6. Выйти\r\n")
								.append("Имя: " + tracker.items[0].getName() + " ID " + tracker.items[0].getId() + "\r\n\r\n")
								.toString()
				)
		);
	}

}