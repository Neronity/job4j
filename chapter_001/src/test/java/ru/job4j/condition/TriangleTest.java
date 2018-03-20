package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class TriangleTest {
 	@Test
	public void whenAreaSetThreePointsThenTriangleArea() {
 		Point a = new Point(0, 0);
 		Point b = new Point(0, 2);
 		Point c = new Point(2, 0);
 		Triangle triangle = new Triangle(a, b, c);
 		double result = triangle.area();
 		double expected = 2D;
 		assertThat(result, closeTo(expected, 0.1));
 	}

 	@Test
 	public void whenPeriodSetThreeSidesThenPeriod() {
 		Point a = new Point(0, 0);
 		Point b = new Point(0, 2);
 		Point c = new Point(2, 0);
 		Triangle triangle = new Triangle(a, b, c);
 		double ab = 2;
    	double ac = 3;
    	double bc = 1;
    	assertThat(triangle.period(ab, ac, bc), closeTo(3D, 0.1));

 	}

 	@Test
 	public void whenCanNotExistThenFalse() {
 		Point a = new Point(0, 0);
 		Point b = new Point(0, 2);
 		Point c = new Point(2, 0);
 		Triangle triangle = new Triangle(a, b, c);
 		double ab = 2D;
    	double ac = 1D;
    	double bc = 17D;
    	assertThat(triangle.exist(ab, ac, bc), is(false));
 	}
}