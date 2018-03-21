package ru.job4j.loop;

/**
* Пирамида
*/
public class Paint {

    /**
    * Правая часть пирамиды
    * @param height высота пирамиды
    * @return рисунок пирамиды
    */
	public String rightTrl(int height) {
		StringBuilder screen = new StringBuilder();
		int width = height;
		for (int row = 0; row != height; row++) {
			for (int column = 0; column != width; column++) {
				if (row >= column) {
					screen.append("^");
				} else {
					screen.append(" ");
				}
			}
			screen.append(System.lineSeparator());
		}
		return screen.toString();
	}

    /**
    * Левая часть пирамиды
    * @param height высота пирамиды
    * @return рисунок пирамиды
    */
	public String leftTrl(int height) {
    	StringBuilder screen = new StringBuilder();
    	int width = height;
    	for (int row = 0; row != height; row++) {
        	for (int column = 0; column != width; column++) {
            	if (row >= width - column - 1) {
                	screen.append("^");
            	} else {
                	screen.append(" ");
            	}
        	}
        	screen.append(System.lineSeparator());
    	}
    	return screen.toString();
	}

    /**
    * Целая пирамида
    * @param height высота пирамиды
    * @return рисунок пирамиды
    */
	public String pyramid(int height) {
    	StringBuilder screen = new StringBuilder();
    	int width = 2 * height - 1;
    	for (int row = 0; row != height; row++) {
        	for (int column = 0; column != width; column++) {
            	if (row >= height - column - 1 && row + height - 1 >= column) {
                	screen.append("^");
            	} else {
                	screen.append(" ");
            	}
        	}
        	screen.append(System.lineSeparator());
    	}
    	return screen.toString();
	}
}