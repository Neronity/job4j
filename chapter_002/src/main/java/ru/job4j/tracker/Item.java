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
}