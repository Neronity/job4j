package ru.job4j.tracker;

public interface UserAction {

	/**
	 * @return порядковый номер команды (число, которое вводит пользователь)
	 */
	int key();

	/**
	 * Выполнение события
	 * @param input способ ввода
	 * @param tracker хранилище (трекер)
	 */
	void execute(Input input, ITracker tracker);

	/**
	 * @return информация о команде для главного меню
	 */
	String info();
}