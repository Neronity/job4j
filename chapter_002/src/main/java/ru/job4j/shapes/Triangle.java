package ru.job4j.shapes;

public class Triangle implements Shape {

	public String draw() {
		StringBuilder pic = new StringBuilder();
    	pic.append("   +   ");
    	pic.append("  +++  ");
    	pic.append(" +++++ ");
    	pic.append("+++++++");
    	return pic.toString();
	}
}