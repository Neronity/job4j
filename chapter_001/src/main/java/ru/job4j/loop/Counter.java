package ru.job4j.loop;

/**
* Сумма четных чисел
*/
public class Counter {

	/**
	* Подсчет суммы четных чисело в заданном промежутке
	* @param start левая граница промежутка
	* @param finish правая граница промежутка
	* @return сумма
	*/
	public int sumOfEvens(int start, int finish) {
		int sum = 0;
		for (int i = start; i <= finish; i++) {
			if (i % 2 == 0) {
				sum += i;
			}
		}
		return sum;
	}
}