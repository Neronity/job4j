package ru.job4j.tracker;

public class ValidateInput extends ConsoleInput {

	public int ask(String question, int[] range) {
		boolean invalid = true;
		int value = -1;
		do {
			try {
				value = super.ask(question, range);
				invalid = false;
			} catch (NumberFormatException nfe) {
				System.out.println("Введено не число! Пожалуйста, введите число!");
			} catch (MenuOutOfRangeException mor) {
				System.out.println("Введен неверный пункт меню! Пожалуйста, введите число из диапазона меню!");
			}
		} while (invalid);
		return value;
	}
}