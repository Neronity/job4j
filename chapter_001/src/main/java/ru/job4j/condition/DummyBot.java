package ru.job4j.condition;

/**
* Глупый бот
*/
public class DummyBot {
	/**
	* Ответ на вопрос 
	* @param question вопрос боту
	* @return ответ бота
	*/
	public String answer(String question) {
		String rsl;

		if (question.equals("Привет, Бот.")) {
			rsl = "Привет, умник.";

		} else if (question.equals("Пока.")) {
			rsl = "До свидания.";
		} else {
			rsl = "Это ставит меня в тупик. Спросите другой вопрос.";
		}
		return rsl;
	}
}