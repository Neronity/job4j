package ru.job4j.calculator;

/**
 *
 */
public class Calculator {
    private double result;

    /**
     * Сложение
     * @param first первое слогаемое
     * @param second второе слогаемое
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Вычитание
     * @param first уменьшаемое
     * @param second вычитаемое
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Умножение
     * @param first первый множитель
     * @param second второй множитель
     */
    public void multiply(double first, double second) {
        this.result = first * second;
    }

    /**
     * Деление
     * @param first делимое
     * @param second делитель
     */
    public void deivide(double first, double second) {
        this.result = first / second;
    }

    public double getResult() {
        return this.result;
    }
}
