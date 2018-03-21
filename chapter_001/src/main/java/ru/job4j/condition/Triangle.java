package ru.job4j.condition;

import java.lang.Math;

public class Triangle {
   private Point a;
   private Point b;
   private Point c;

   public Triangle(Point a, Point b, Point c) {
       this.a = a;
       this.b = b;
       this.c = c;
    }

    /**
    * Проверка возможности построения теругольника по заданным сторонам
    * 
    * @param ab расстояние между точками a b
	* @param ac расстояние между точками a c
	* @param bc расстояние между точками b c
    * @return true, если каждая сторона меньше суммы двух других сторон, иначе false
    *
    */
	public boolean exist(double ab, double ac, double bc) {
		return ab < (bc + ac) && bc < (ab + ac) && ac < (ab + bc);
	}

    /**
	* Метод вычисления полупериметра по длинам сторон.
	*
	* Формула.
	*
	* (ab + ac + bc) / 2
	*
	* @param ab расстояние между точками a b
	* @param ac расстояние между точками a c
	* @param bc расстояние между точками b c
	* @return Перимент.
	*/
	public double period(double ab, double ac, double bc) {
    	return (ab + ac + bc) / 2;
    }

    /**
	* Метод должен вычислить площадь треугольника.
	*
	* @return Вернуть прощадь, если треугольник существует или -1, если треугольника нет.
	*/
	public double area() {
    	double rsl = -1D;
    	double ab = this.a.distanceTo(this.b);
    	double ac = this.a.distanceTo(this.c);
    	double bc = this.b.distanceTo(this.c);
    	double p = this.period(ab, ac, bc);
    	if (this.exist(ab, ac, bc)) {
        	rsl = Math.sqrt(p * (p - ab) * (p - bc) * (p - ac));
    	}
    	return rsl;
	}

}