package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {
	@Test
	public void whenOneAndTwoThenTwo() {
		Max max = new Max();
		assertThat(max.max(1, 2), is(2));
	}

	@Test
	public void whenOneTwoAndThreeThenThree() {
		Max max = new Max();
		assertThat(max.max(1, 2, 3), is(3));
	}
}