package ru.job4j.array;

import java.util.Arrays;

public class ArrayDuplicate {

	public String[] remove(String[] arr) {
		int unique = arr.length;
		for (int outer = 0; outer < unique; outer++) {
			for (int inner = outer + 1; inner < unique; inner++) {
				if (arr[outer].equals(arr[inner])) {
					arr[inner] = arr[unique - 1];
					unique--;
					inner--;
				}
			}
		}
		return Arrays.copyOf(arr, unique);
	}
}