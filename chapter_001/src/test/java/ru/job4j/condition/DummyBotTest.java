package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DummyBotTest {
	@Test
	public void whenGreetBot() {
		DummyBot bot = new DummyBot();
		assertThat(bot.answer("Привет, Бот."), is("Привет, умник."));
	}

	@Test
	public void whenByeBot() {
		DummyBot bot = new DummyBot();
		assertThat(bot.answer("Пока."), is("До свидания."));
	}

	@Test
	public void whenUnknown() {
		DummyBot bot = new DummyBot();
		assertThat(bot.answer("TESTTEST"),
		 is("Это ставит меня в тупик. Спросите другой вопрос."));
	}
}