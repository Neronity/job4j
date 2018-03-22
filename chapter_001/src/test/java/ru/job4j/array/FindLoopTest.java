package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Andrey Sigachev
 * @version $Id$
 * @since 0.1
 */
public class FindLoopTest {

	@Test
	public void whenElInArrayThenIndex() {
		FindLoop find = new FindLoop();
		assertThat(find.indexOf(new int[] {1, 2, 3, 4, 5}, 5), is(4));
	}

	@Test
	public void whenElNotInArrayThenMinusOne() {
		FindLoop find = new FindLoop();
		assertThat(find.indexOf(new int[] {1, 2, 3, 4, 5}, 56), is(-1));
	}
}