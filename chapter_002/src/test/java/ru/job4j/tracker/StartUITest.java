package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

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
   		Input input = new StubInput(Arrays.asList("0", "test name", "desc", "6", "y"));
   		new StartUI(input, tracker).init();
   		assertThat(tracker.getAll().get(0).getName(), is("test name"));
	}

 	@Test
 	public void whenReplaceThenTrackerHasSameItemList() {
    	Tracker tracker = new Tracker();
    	Item item = new Item("test", "test desc", null);
    	String id = tracker.add(item);
    	Input input = new StubInput(Arrays.asList("1", id, "test name", "desc", "6", "y"));
    	new StartUI(input, tracker).init();
    	assertThat(tracker.getAll().get(0).getId(), is(id));
 	}

 	@Test
 	public void whenDeleteItemThenTrackerHasRemainingItems() {
 		Tracker tracker = new Tracker();
    	Item item = new Item("test", "test desc", null);
		String id = tracker.add(item);
		Item item1 = new Item("test1", "test1 desc", null);
		tracker.add(item1);
    	Input input = new StubInput(Arrays.asList("2", id, "6", "y"));
    	new StartUI(input, tracker).init();
    	assertThat(tracker.getAll().get(0), is(item1));

 	}

 	@Test
 	public void whenFindingByIdThenCorrectItem() {
 		Tracker tracker = new Tracker();
    	Item item = new Item("test", "test desc", null);
    	tracker.add(item);
    	Item item1 = new Item("test1", "test1 desc", null);
    	String id = tracker.add(item1);
    	Input input = new StubInput(Arrays.asList("3", id, "6", "y"));
    	new StartUI(input, tracker).init();
    	assertThat(tracker.getAll().get(1).getId(), is(id));
 	}

 	@Test
 	public void whenFindingByNameThenCorrectItem() {
 		Tracker tracker = new Tracker();
    	Item item = new Item("test", "test desc", null);
    	tracker.add(item);
    	Item item1 = new Item("test1", "test1 desc", null);
    	tracker.add(item1);
    	Input input = new StubInput(Arrays.asList("4", item.getName(), "6", "y"));
		new StartUI(input, tracker).init();
    	assertThat(tracker.getAll().get(0).getName(), is("test"));
 	}

	@Test
	public void whenShowMenuThenMenu() {
		new StartUI(new StubInput(Arrays.asList("6", "y")), new Tracker()).init();
		assertThat(new String(out.toByteArray()),
				is(new StringBuilder()
				.append("0. Создать заявку\n")
				.append("1. Заменить заявку\n")
				.append("2. Удалить заявку\n")
				.append("3. Поиск по ID заявки\n")
				.append("4. Поиск по имени заявки\n")
				.append("5. Список всех заявок\n")
				.append("6. Выйти\n")
				.toString()
				)
		);
	}

	@Test
	public void whenCreateItemThenText() {
		Tracker tracker = new Tracker();
		Input input = new StubInput(Arrays.asList("0", "test name", "desc", "6", "y"));
		new StartUI(input, tracker).init();
		assertThat(new String(this.out.toByteArray()),
				is(
						new StringBuilder()
								.append("0. Создать заявку\n")
								.append("1. Заменить заявку\n")
								.append("2. Удалить заявку\n")
								.append("3. Поиск по ID заявки\n")
								.append("4. Поиск по имени заявки\n")
								.append("5. Список всех заявок\n")
								.append("6. Выйти\n")
								.append("------------ Добавление новой заявки --------------\n")
								.append("------------ Новая заявка с ID : " + tracker.getAll().get(0).getId() + "-----------\n\n")
								.toString()
				)
		);
	}

	@Test
	public void whenReplaceItemThenText() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("test", "desc", null));
		Input input = new StubInput(Arrays.asList("1", tracker.getAll().get(0).getId(), "test name", "desc", "6", "y"));
		new StartUI(input, tracker).init();
		assertThat(new String(this.out.toByteArray()),
				is(
						new StringBuilder()
								.append("0. Создать заявку\n")
								.append("1. Заменить заявку\n")
								.append("2. Удалить заявку\n")
								.append("3. Поиск по ID заявки\n")
								.append("4. Поиск по имени заявки\n")
								.append("5. Список всех заявок\n")
								.append("6. Выйти\n")
								.append("------------ Замена существующей заявки --------------\n")
								.append("------------ Заявка успешно заменена -----------\n\n")
								.toString()
				)
		);
	}

	@Test
	public void whenDeleteItemThenText() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("test", "desc", null));
		Input input = new StubInput(Arrays.asList("2", tracker.getAll().get(0).getId(), "6", "y"));
		new StartUI(input, tracker).init();
		assertThat(new String(this.out.toByteArray()),
				is(
						new StringBuilder()
								.append("0. Создать заявку\n")
								.append("1. Заменить заявку\n")
								.append("2. Удалить заявку\n")
								.append("3. Поиск по ID заявки\n")
								.append("4. Поиск по имени заявки\n")
								.append("5. Список всех заявок\n")
								.append("6. Выйти\n")
								.append("------------ Удаление существующей заявки --------------\n")
								.append("------------ Заявка успешно удалена -----------\n\n")
								.toString()
				)
		);
	}

	@Test
	public void whenFindingItemThenCorrectInfo() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("test", "desc", null));
		Input input = new StubInput(Arrays.asList("3", tracker.getAll().get(0).getId(), "6", "y"));
		new StartUI(input, tracker).init();
		assertThat(new String(this.out.toByteArray()),
				is(
						new StringBuilder()
								.append("0. Создать заявку\n")
								.append("1. Заменить заявку\n")
								.append("2. Удалить заявку\n")
								.append("3. Поиск по ID заявки\n")
								.append("4. Поиск по имени заявки\n")
								.append("5. Список всех заявок\n")
								.append("6. Выйти\n")
								.append("------------ Поиск заявки по ID --------------\n")
								.append("------------ Имя заявки --------------\n" + tracker.getAll().get(0).getName() + "\n")
								.append("------------ Описание заявки --------------\n" + tracker.getAll().get(0).getDesc() + "\n")
								.append("Уникальный ID: " + tracker.getAll().get(0).getId() + "\n\n")
								.toString()
				)
		);
	}

	@Test
	public void whenFindingItemByNameThenCorrectInfo() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("test", "desc", null));
		Input input = new StubInput(Arrays.asList("4", tracker.getAll().get(0).getName(), "6", "y"));
		new StartUI(input, tracker).init();
		assertThat(new String(this.out.toByteArray()),
				is(
						new StringBuilder()
								.append("0. Создать заявку\n")
								.append("1. Заменить заявку\n")
								.append("2. Удалить заявку\n")
								.append("3. Поиск по ID заявки\n")
								.append("4. Поиск по имени заявки\n")
								.append("5. Список всех заявок\n")
								.append("6. Выйти\n")
								.append("------------ Поиск заявки по имени --------------\n")
								.append("------------ Имя заявки --------------\n" + tracker.getAll().get(0).getName() + "\n")
								.append("------------ Описание заявки --------------\n" + tracker.getAll().get(0).getDesc() + "\n")
								.append("Уникальный ID: " + tracker.getAll().get(0).getId() + "\n\n")
								.toString()
				)
		);
	}

	@Test
	public void whenGetListOfItemsThenCorrectInfo() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("test", "desc", null));
		Input input = new StubInput(Arrays.asList("5", "6", "y"));
		new StartUI(input, tracker).init();
		assertThat(new String(this.out.toByteArray()),
				is(
						new StringBuilder()
								.append("0. Создать заявку\n")
								.append("1. Заменить заявку\n")
								.append("2. Удалить заявку\n")
								.append("3. Поиск по ID заявки\n")
								.append("4. Поиск по имени заявки\n")
								.append("5. Список всех заявок\n")
								.append("6. Выйти\n")
								.append("Имя: " + tracker.getAll().get(0).getName() + " ID " + tracker.getAll().get(0).getId() + "\n\n")
								.toString()
				)
		);
	}

}