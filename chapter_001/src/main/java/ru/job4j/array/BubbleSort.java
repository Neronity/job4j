package ru.job4j.array;

public class BubbleSort {
	public int[] sort(int[] arr) {
		int counter = arr.length;
		while (counter != 0) {
			for (int i = 0; i < arr.length - 1; i++) {
				if (arr[i] > arr[i + 1]) {
					int tmp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = tmp;
				}
			}
			counter--;
		}
		return arr;
	}
}