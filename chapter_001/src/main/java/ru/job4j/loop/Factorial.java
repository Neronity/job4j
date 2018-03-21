package ru.job4j.loop;

/**
* Вычисление факториала
*/
public class Factorial {

	/**
	* Факториал заданного числа
	* @param number число, для которого вычисляется факториал
	* @return результат вычислений
	*/
	public int calc(int number) {
		int res = 1;
		for (int i = 1; i <= number; i++) {
			res *= i;
		}
		return res;
	}
}