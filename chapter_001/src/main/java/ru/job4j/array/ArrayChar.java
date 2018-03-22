package ru.job4j.array;

/**
 * Начинается ли слово с указанной последовательности символов
 */
public class ArrayChar {
	private char[] data;

	/**
	 * Разбиение слова на последовательность символов
	 * @param word слово, которое разбивается
	 */
	public ArrayChar(String word) {
		data = word.toCharArray();
	}

	/**
	 * Проверка слова на наличие указанного префикса
	 * @param prefix префикс
	 * @return true/false
	 */
	public boolean startWith(String prefix) {
		boolean result = true;
		char[] pref = prefix.toCharArray();
		for (int index = 0; index < pref.length; index++) {
			if (pref[index] != data[index]) {
				result = false;
				break;
			}
		}
		return result;
	}
}