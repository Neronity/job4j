package ru.job4j.calculator;

/**
 * Конвертер валюты
 */
public class Converter {

    /**
     * Рубли в евро
     * @param value сумма в рублях
     * @return конвертированная сумма в евро
     */
    public int rubleToEuro(int value) {
        return value / 70;
    }

    /**
     * Рубли в доллары
     * @param value сумма в рублях
     * @return конвертированная сумма в долларах
     */
    public int rubleToDollar(int value) {
        return value / 60;
    }

    /**
     * Доллары в рубли
     * @param value сумма в долларах
     * @return конвертированная сумма в рублях
     */
    public int dollarToRuble(int value) {
        return value * 60;
    }

    /**
     * Евро в рубли
     * @param value сумма в евро
     * @return конвертированная сумма в рублях
     */
    public int euroToRuble(int value) {
        return value * 70;
    }
}
