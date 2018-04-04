package ru.job4j.tracker;

public class MenuOutOfRangeException extends RuntimeException {

	public MenuOutOfRangeException(String message) {
		System.out.println(message);
	}
}