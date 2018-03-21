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
public class CounterTest {

	@Test
	public void whenOneToTenThenThirty() {
		Counter count = new Counter();
		assertThat(count.sumOfEvens(1, 10), is(30));
	}
}