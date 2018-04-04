package ru.job4j.tracker;


public class GetAllItems implements UserAction {
	public int key() {
			return 5;
	}

	public void execute(Input input, Tracker tracker) {
		for (Item item : tracker.getAll()) {
            	System.out.println("Имя: " + item.getName() + " ID " + item.getId());
        }
        System.out.println("");
	}

	public String info() {
		return String.format("%s. %s", key(), "Список всех заявок");
	}
}