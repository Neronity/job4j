package ru.job4j.tracker;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";

    /**
    * Константа для замены существующей заявки
    */
    private static final String REPLACE = "1";

    /**
    * Константа для удаление существующей заявки
    */
    private static final String DELETE = "2";

    /**
    * Константа для поиска по ID
    */
    private static final String FINDBYID = "3";

    /**
    * Константа для поиска по имени
    */
    private static final String FINDBYNAME = "4";

    /**
    * Константа для отображения списка заявок
    */
    private static final String GETALL = "5";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

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
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            switch (answer) {
                case ADD:
                    createItem();
                    break;
                case REPLACE:
                    replaceItem();
                    break;
                case DELETE:
                    deleteItem();
                    break;
                case FINDBYID:
                    findItemById();
                    break;
                case FINDBYNAME:
                    findItemByName();
                    break;
                case GETALL:
                    getAllItems();
                    break;
                case EXIT:
                    exit = true;
                    break;
                default:
                    System.out.println("Неправильный запрос, повторите попытку!\n");
                    break;
            }
        }
    }

    /**
     * Метод реализует добавление новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой языки --------------");
        String name = input.ask("Введите имя заявки :");
        String desc = input.ask("Введите описание заявки :");
        Item item = new Item(name, desc, null);
        tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------\n");
    }

    /**
    * Замена существующей заявки на новую
    */
    private void replaceItem() {
        System.out.println("------------ Замена существующей заявки --------------");
        String id = input.ask("Введите уникальный ID заявки, которую хотите заменить:");
        String name = input.ask("Введите имя новой заявки :");
        String desc = input.ask("Введите описание новой заявки :");
        Item item = new Item(name, desc, id);
        tracker.replace(id, item);
        System.out.println("------------ Заявка успешно заменена -----------\n");
    }

    /**
    * Удаление сущесвтующей заявки
    */
    private void deleteItem() {
        System.out.println("------------ Удаление существующей заявки --------------");
        String id = input.ask("Введите уникальный ID заявки, которую хотите удалить:");
        tracker.delete(id);
        System.out.println("------------ Заявка успешно удалена -----------\n");
        
    }

    /**
    * Поиск заявки по ID
    */
    private void findItemById() {
        System.out.println("------------ Поиск заявки по ID --------------");
        String id = input.ask("Введите уникальный ID заявки, которую хотите найти:");
        showItemInfo(tracker.findById(id));
    }

    /**
    * Поиск заявки по имени
    */
    private void findItemByName() {
        System.out.println("------------ Поиск заявки по имени --------------");
        String name = input.ask("Введите полное имя заявки, которую хотите найти:");
        showItemInfo(tracker.findByName(name));
    }

    /**
    * Вывод списка всех заявок
    */
    private void getAllItems() {
        for (Item item : tracker.getAll()) {
            System.out.println("Имя: " + item.getName() + " ID " + item.getId());
        }
        System.out.println("");
    }

    /*
    * Отображение меню для пользователя
    */
    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Создать заявку");
        System.out.println("1. Заменить заявку");
        System.out.println("2. Удалить заявку");
        System.out.println("3. Поиск по ID заявки");
        System.out.println("4. Поиск по имени заявки");
        System.out.println("5. Список всех заявок\n");
    }

    /**
    * Вывод информации о заявке на экран
    */
    private void showItemInfo(Item item) {
        System.out.println("------------ Имя заявки --------------\n" + item.getName());
        System.out.println("------------ Описание заявки --------------\n" + item.getDesc());
        System.out.println("Уникальный ID: " + item.getId() + "\n");
    }

    /**
     * Запуск программы.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}