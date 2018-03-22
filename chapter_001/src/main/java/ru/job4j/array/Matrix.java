package ru.job4j.array;

/**
 * Построение таблицы умножения заданного размера
 */
public class Matrix {

	/**
	 * Построение таблицы по заданному размеру
	 * @param size размер таблицы
	 * @return таблица
	 */
	public int[][] multiple(int size) {
		int[][] matrix = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matrix[i][j] = (i + 1) * (j + 1);
			}
		}
		return matrix;
	}
}