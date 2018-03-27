package ru.job4j.array;

/**
 * Содержит ли одно слово другое
 */
public class WordContaining {

    /**
     * Проверка на содержание одного слова в другом
     * @param origin начальное слово
     * @param sub слово, которое потенциально содержится в начальном слове
     * @return true/false
     */
    public boolean containWord(String origin, String sub) {
		boolean result = false;
		char[] originChars = origin.toCharArray();
		char[] subChars = sub.toCharArray();
		for (int outer = 0; outer <= originChars.length - subChars.length + 1; outer++) {
			if (result) {
				break;
			}
			for (int subIndex = 0; subIndex < subChars.length; subIndex++) {
				if (subChars[subIndex] != (originChars[subIndex + outer])) {
					break;
				} else if (subIndex == subChars.length - 1) {
					result = true;
				}
			}
		}
		return result;
	}
}