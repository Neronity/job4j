package ru.job4j.tracker;

import java.util.*;

public class ConsoleInput implements Input {

	public String ask(String question) {
		Scanner scan = new Scanner(System.in);
		System.out.println(question);
		return scan.next();

	}

	public int ask(String question, int[] range) throws MenuOutOfRangeException {
		int key = Integer.parseInt(ask(question));
		boolean exist = false;
		for (int value : range) {
			if (value == key) {
				exist = true;
				break;
			}
		}
		if (exist) {
			return key;
		} else {
			throw new MenuOutOfRangeException();
		}
	}
}