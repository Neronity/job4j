package ru.job4j.tracker;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Выйти ли из программы
     */
    public static boolean exit = false;


    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        Menu menu = new Menu(input, tracker);
        menu.fillActions();
        int[] actions = new int[menu.actions.length];
        for (int index = 0; index < menu.actions.length; index++) {
            actions[index] = menu.actions[index].key();
        }
        do {
            menu.show();
            menu.select(input.ask("Выберите действие: ", actions));
        } while (!exit);
    }


    /**
     * Запуск программы.
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}