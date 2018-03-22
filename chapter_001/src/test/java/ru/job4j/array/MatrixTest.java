package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Andrey Sigachev
 * @version $Id$
 * @since 0.1
 */
public class MatrixTest {

	@Test
	public void whenFiveThenMultipleTable() {
		Matrix mtr = new Matrix();
		assertThat(mtr.multiple(5), is(new int[][]{{1, 2, 3, 4, 5},
										{2, 4, 6, 8, 10},
										{3, 6, 9, 12, 15},
										{4, 8, 12, 16, 20},
										{5, 10, 15, 20, 25}
										}));
	}
}