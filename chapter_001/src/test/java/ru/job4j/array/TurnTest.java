package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TurnTest {

	@Test
	public void whenElementsNumberIsOddThenTurnedArray() {
		Turn trn = new Turn();
		assertThat(trn.back(new int[] {1, 2, 3, 4, 5}), is(new int[] {5, 4, 3, 2, 1}));
	}

	@Test
	public void whenElementsNumberIsEvenThenTurnedArray() {
		Turn trn = new Turn();
		assertThat(trn.back(new int[] {1, 2, 3, 4, 5, 6}), is(new int[] {6, 5, 4, 3, 2, 1}));
	}
}