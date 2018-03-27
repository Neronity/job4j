package ru.job4j.profession;

public class Engineer extends Profession {

	public House buildHouse() {
		return new House(10, 10, 10);
	}
}