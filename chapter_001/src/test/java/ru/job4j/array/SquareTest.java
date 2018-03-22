package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Andrey Sigachev
 * @version $Id$
 * @since 0.1
 */
public class SquareTest {
		@Test
		public void whenBound3Then149() {
			int bound = 3;
			Square square = new Square();
			int[] rst = square.calculate(bound);
			int[] expect = new int[] {1, 4, 9};
			assertThat(rst, is(expect));
		}
}