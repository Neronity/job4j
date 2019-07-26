package ru.job4j.tracker;

public class Item {
	private String id;
	private String name;
	private String desc;
	private long created;
	private String[] comments;

	public Item(String name, String desc, String id) {
		this.name = name;
		this.desc = desc;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	/**
	 * Отображение информации о заявке для поиска по Id и имени
	 */
	public void showItemInfo() {
		System.out.println("------------ Имя заявки --------------" + this.name);
		System.out.println("------------ Описание заявки --------------" + this.desc);
		System.out.println("Уникальный ID: " + this.id + "");
	}

}