package ru.job4j.loop;

/**
* Шахматная доска
*/
public class Board {
     /**
     * Отрисовка шахматной доски
     * @param width ширина доски
     * @param height высота доски
     * @return рисунок доски
     */
	public String paint(int width, int height) {
		StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i  <= height - 1; i++) {
            for (int j = 0; j <= width - 1; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}