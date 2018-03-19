package ru.job4j.calculator;

/**
 * Рассчет идеального веса в соответствии с ростом
 */
public class Fit {

    /**
     * Вес мужчины
     * @param height рост мужчины
     * @return идеальный вес
     */
    public double manWeight(double height) {
        return (height - 100) * 1.15;
    }

    /**
     * Вес женщины
     * @param height рост женщины
     * @return идеальный вес
     */
    public double womanWeight(double height) {
        return (height - 110) * 1.15;
    }
}
