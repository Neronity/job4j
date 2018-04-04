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
		actions[0] = new AddItem();
		actions[1] = new Menu.ReplaceItem();
		actions[2] = new DeleteItem();
		actions[3] = new FindById();
		actions[4] = new FindByName();
		actions[5] = new GetAllItems();
		actions[6] = new Exit();

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
	private class AddItem implements UserAction {
		public int key() {
			return 0;
		}

		public void execute(Input input, Tracker tracker) {
			System.out.println("------------ Добавление новой заявки --------------");
        	String name = input.ask("Введите имя заявки :");
        	String desc = input.ask("Введите описание заявки :");
        	Item item = new Item(name, desc, null);
        	tracker.add(item);
        	System.out.println("------------ Новая заявка с ID : " + item.getId() + "-----------\n");
		}

		public String info() {
			return String.format("%s. %s", key(), "Создать заявку");
		}
	}

	/**
	 * Замена существующей заявки на новую
	 */
	public static class ReplaceItem implements UserAction {
		public int key() {
			return 1;
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

		public String info() {
			return String.format("%s. %s", key(), "Заменить заявку");
		}
	}

	/**
	 * Удаление сущесвтующей заявки
	 */
	private class DeleteItem implements UserAction {
		public int key() {
			return 2;
		}

		public void execute(Input input, Tracker tracker) {
			System.out.println("------------ Удаление существующей заявки --------------");
        	String id = input.ask("Введите уникальный ID заявки, которую хотите удалить:");
        	tracker.delete(id);
        	System.out.println("------------ Заявка успешно удалена -----------\n");
		}

		public String info() {
			return String.format("%s. %s", key(), "Удалить заявку");
		}
	}

	/**
	 * Поиск заявки по ID
	 */
	private class FindById implements UserAction {
		public int key() {
			return 3;
		}

		public void execute(Input input, Tracker tracker) {
			System.out.println("------------ Поиск заявки по ID --------------");
        	String id = input.ask("Введите уникальный ID заявки, которую хотите найти:");
        	tracker.findById(id).showItemInfo();
		}

		public String info() {
			return String.format("%s. %s", key(), "Поиск по ID заявки");
		}
	}

	/**
	 * Поиск заявки по имени
	 */
	private class FindByName implements UserAction {
		public int key() {
			return 4;
		}

		public void execute(Input input, Tracker tracker) {
			System.out.println("------------ Поиск заявки по имени --------------");
        	String name = input.ask("Введите полное имя заявки, которую хотите найти:");
        	tracker.findByName(name).showItemInfo();
		}

		public String info() {
			return String.format("%s. %s", key(), "Поиск по имени заявки");
		}
	}

	/**
	 * Выход из программы
	 */
	private class Exit implements UserAction {
		public int key() {
			return 6;
		}

		public void execute(Input input, Tracker tracker) {
			String answer = input.ask("Вы уверены, что хотите выйти? y/n");
			if ("y".equals(answer)) {
				StartUI.exit = true;
			}
		}

		public String info() {
			return String.format("%s. %s", key(), "Выйти");
		}
	}
}