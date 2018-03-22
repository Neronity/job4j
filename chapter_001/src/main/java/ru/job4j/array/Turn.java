package ru.job4j.array;

public class Turn {
	public int[] back(int[] arr) {
		for (int i = 0; i <= (arr.length - 1) / 2; i++) {
			int tmp = arr[i];
			arr[i] = arr[arr.length - i - 1];
			arr[arr.length - i - 1] = tmp;
		}
		return arr;
	}
}