package ru.job4j.tracker;


public class GetAllItems extends BaseAction {

	public GetAllItems(int key, String name) {
		super(key, name);
	}

	public void execute(Input input, ITracker tracker) {
		for (Item item : tracker.getAll()) {
            	System.out.println("Имя: " + item.getName() + " ID " + item.getId());
        }
	}
}