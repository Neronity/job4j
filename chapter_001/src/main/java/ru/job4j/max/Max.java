package ru.job4j.max;

/**
* Максимум из ряда чисел
*/
public class Max {
	/**
	*  Наибольшее из 2 чисел
	* @param firstNum 1 число
	* @param secondNum 2 число
	* @return наибольшее из 2 значений
	*/
	public int max(int firstNum, int secondNum) {
		return firstNum > secondNum ? firstNum : secondNum;
	}

	/**
	 * Наибольшее из 3 чисел
	 * @param firstNum 1 число
	 * @param secondNum 2 число
	 * @param thirdNum 3 число
	 * @return наибольшее из 3 чисел
	 */
	public int max(int firstNum, int secondNum, int thirdNum) {
		return max(max(firstNum, secondNum), max(secondNum, thirdNum));
	}
}