package ru.job4j.array;

/**
 * Массив квадратов
 */
public class Square {

	/**
	 * Создание массива квадратов чисел от 1 до указанной границы
	 * @param bound граница
	 * @return
	 */
	public int[] calculate(int bound) {
		int[] arr = new int[bound];
		for (int i = 1; i <= bound; i++) {
			arr[i - 1] = i * i;
		}
		return arr;
	}
}