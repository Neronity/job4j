package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {

	@Test
	public void whenDuplicatesThenUniques() {
		ArrayDuplicate arr = new ArrayDuplicate();
		assertThat(arr.remove(new String[] {"Melon", "Cherry", "Melon", "Melon", "Pear", "Grape"}),
		 				is(new String[] {"Melon", "Cherry", "Grape", "Pear"}));
	}
}