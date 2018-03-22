package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Andrey Sigachev
 * @version $Id$
 * @since 0.1
 */
public class WordContainingTest {
	
	@Test
	public void whenWordInOtherWordThenTrue() {
		WordContaining wc = new WordContaining();
		assertThat(wc.containWord("Параноидальный", "ный"), is(true));
	}

	@Test
	public void whenWordNotInOtherWordThenFalse() {
		WordContaining wc = new WordContaining();
		assertThat(wc.containWord("Привет", "asd"), is(false));
	}
}