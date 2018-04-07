package ru.job4j.tracker;

public class Menu {

	private Input input;
	private Tracker tracker;

	/**
	 * Массив с действиями
	 */
	public UserAction[] actions = new UserAction[7];

	public Menu(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	/**
	 * Заполнение массива действий
	 */
	public void fillActions() {
		actions[0] = new AddItem(0, "Создать заявку");
		actions[1] = new Menu.ReplaceItem(1, "Заменить заявку");
		actions[2] = new DeleteItem(2, "Удалить заявку");
		actions[3] = new FindById(3, "Поиск по ID заявки");
		actions[4] = new FindByName(4, "Поиск по имени заявки");
		actions[5] = new GetAllItems(5, "Список всех заявок");
		actions[6] = new Exit(6, "Выйти");

	}

	/**
	 * Выполнение выбранного пользователем действия
	 * @param key номер действия, которое нужно выполнить
	 */
	public void select(int key) {
		actions[key].execute(input, tracker);
	}

	/**
	 * Отображение меню
	 */
	public void show() {
		for (UserAction action : actions) {
			System.out.println(action.info());
		}
	}

	/**
	 * Добавление элемента
	 */
	private class AddItem extends BaseAction {

		public AddItem(int key, String name) {
			super(key, name);
		}

		public void execute(Input input, Tracker tracker) {
			System.out.println("------------ Добавление новой заявки --------------");
        	String name = input.ask("Введите имя заявки :");
        	String desc = input.ask("Введите описание заявки :");
        	Item item = new Item(name, desc, null);
        	tracker.add(item);
        	System.out.println("------------ Новая заявка с ID : " + item.getId() + "-----------\n");
		}
	}

	/**
	 * Замена существующей заявки на новую
	 */
	public static class ReplaceItem extends BaseAction {

		public ReplaceItem(int key, String name) {
			super(key, name);
		}

		public void execute(Input input, Tracker tracker) {
			System.out.println("------------ Замена существующей заявки --------------");
        	String id = input.ask("Введите уникальный ID заявки, которую хотите заменить:");
        	String name = input.ask("Введите имя новой заявки :");
        	String desc = input.ask("Введите описание новой заявки :");
        	Item item = new Item(name, desc, id);
        	tracker.replace(id, item);
        	System.out.println("------------ Заявка успешно заменена -----------\n");
		}
	}

	/**
	 * Удаление сущесвтующей заявки
	 */
	private class DeleteItem extends BaseAction {

		public DeleteItem(int key, String name) {
			super(key, name);
		}

		public void execute(Input input, Tracker tracker) {
			System.out.println("------------ Удаление существующей заявки --------------");
        	String id = input.ask("Введите уникальный ID заявки, которую хотите удалить:");
        	tracker.delete(id);
        	System.out.println("------------ Заявка успешно удалена -----------\n");
		}
	}

	/**
	 * Поиск заявки по ID
	 */
	private class FindById extends BaseAction {

		public FindById(int key, String name) {
			super(key, name);
		}

		public void execute(Input input, Tracker tracker) {
			System.out.println("------------ Поиск заявки по ID --------------");
        	String id = input.ask("Введите уникальный ID заявки, которую хотите найти:");
        	tracker.findById(id).showItemInfo();
		}
	}

	/**
	 * Поиск заявки по имени
	 */
	private class FindByName extends BaseAction {

		public FindByName(int key, String name) {
			super(key, name);
		}

		public void execute(Input input, Tracker tracker) {
			System.out.println("------------ Поиск заявки по имени --------------");
        	String name = input.ask("Введите полное имя заявки, которую хотите найти:");
        	tracker.findByName(name).showItemInfo();
		}
	}

	/**
	 * Выход из программы
	 */
	private class Exit extends BaseAction {

		public Exit(int key, String name) {
			super(key, name);
		}

		public void execute(Input input, Tracker tracker) {
			String answer = input.ask("Вы уверены, что хотите выйти? y/n");
			if ("y".equals(answer)) {
				StartUI.exit = true;
			}
		}
	}
}