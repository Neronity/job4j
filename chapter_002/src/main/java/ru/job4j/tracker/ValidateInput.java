package ru.job4j.tracker;

public class ValidateInput implements Input {

	private final Input input;

	public ValidateInput(final Input input) {
		this.input = input;
	}

	@Override
	public String ask(String question) {
		return this.input.ask(question);
	}

	public int ask(String question, int[] range) {
		boolean invalid = true;
		int value = -1;
		do {
			try {
				value = input.ask(question, range);
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