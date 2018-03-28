package ru.job4j.tracker;

import java.util.*;

public class ConsoleInput implements Input {

	public String ask(String question) {
		Scanner scan = new Scanner(System.in);
		System.out.println(question);
		return scan.next();

	}
}