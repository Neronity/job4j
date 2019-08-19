package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
    private final ITracker tracker;

    private final Consumer<String> output;

    /**
     * Выйти ли из программы
     */
    public static boolean exit = false;


    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        Menu menu = new Menu(input, tracker, System.out::println);
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
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker(), System.out::println).init();
    }
}