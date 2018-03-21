package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Andrey Sigachev
 * @version $Id$
 * @since 0.1
 */
public class FactorialTest {

	@Test
	public void whenTenThen3628800() {
		Factorial calc = new Factorial();
		assertThat(calc.calc(10), is(3628800));
	}

	@Test
	public void whenZeroThenOne() {
		Factorial calc = new Factorial();
		assertThat(calc.calc(0), is(1));
	}
}