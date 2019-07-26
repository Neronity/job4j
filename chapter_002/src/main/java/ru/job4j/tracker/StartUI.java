package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

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
        List<Integer> actions = new ArrayList<>();
        for (UserAction a : menu.actions) {
            actions.add(a.key());
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