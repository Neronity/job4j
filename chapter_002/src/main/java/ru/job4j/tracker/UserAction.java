package ru.job4j.tracker;

public interface UserAction {

	/**
	 * @return порядковый номер команды (число, которое вводит пользователь)
	 */
	String key();

	/**
	 * Выполнение события
	 * @param input способ ввода
	 * @param tracker хранилище (трекер)
	 */
	void execute(Input input, Tracker tracker);

	/**
	 * @return информация о команде для главного меню
	 */
	String info();
}