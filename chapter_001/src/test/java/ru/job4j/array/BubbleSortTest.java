package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {

	@Test
	public void whenNotSortedThenSorted() {
		BubbleSort sort = new BubbleSort();
		assertThat(sort.sort(new int[] {4, 3, 6, 1, 2, 5, 9, 7, 8}), is(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}));
	}
}