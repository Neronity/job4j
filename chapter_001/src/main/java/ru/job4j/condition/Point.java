package ru.job4j.condition;

import java.lang.Math;

/**
 * @author Andrey Sigachev
 * @version $Id$
 * @since 0.1
 */
public class Point {
    private int x;
    private int y;

    /**
     * Конструктор класса
     * @param x координата точки x
     * @param y коррдината точки y
     */
    public  Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Вычисление расстояния между точками
     * @param that точка, до которой нужно вчислить расстояние
     * @return расстояние до точки
     */
    public double distanceTo(Point that) {
        return Math.sqrt(
                Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
    }

    public static void main(String[] args) {
        Point a = new Point(0, 1);
        Point b = new Point(2, 5);

        double result = a.distanceTo(b);
        System.out.println("Расстояние между точками А и В : " + result);
    }
}