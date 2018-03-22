package ru.job4j.array;

/**
 * Поиск с помощью циклов
 */
public class FindLoop {

	/**
	 * Поиск методом перебора
	 * @param arr массив, в котором ищем
	 * @param el элемент, который ищем
	 * @return индекс элемента, если он существует
	 */
	public int indexOf(int[] arr, int el) {
		int rsl = -1;
		for (int i = 0; i <= arr.length - 1; i++) {
			if (arr[i] == el) {
				rsl = i;
				break;
			}
		}
		return rsl;
	}
}