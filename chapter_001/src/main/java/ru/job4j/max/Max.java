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
}