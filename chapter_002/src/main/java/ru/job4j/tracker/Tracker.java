package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    public final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     * @return 
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
    * Замена существующей заявки
    * @param id заявка, которую нужно заменить
    * @param item заявка, на которую заменяем
    */
    public void replace(String id, Item item) {
        items[getItemIndex(findById(id).getId())] = item;
    }

    /**
    * Удаление существующей заявки;
    * @param id заявка, которую нужно удалить;
    */
    public void delete(String id) {
        int index = getItemIndex(id);
        for (int i = index; i <= position; i++) {
            if (i == position) {
                items[i] = null;
            } else {
                items[i] = items[i + 1];
            }
        }
    }

    /**
    * Поиск заявки по ID
    * @param id 
    * @return item
    */
    public Item findById(String id) {
        Item result = null;
        for (int i = 0; i < position; i++) {
            if (items[i].getId() == id) {
                result = items[i];
            }
        }
        return result;
    }

    /**
    * Поиск заявки по имени
    * @param name имя заявки
    * @return заявка с указанным именем
    */
    public Item findByName(String name) {
        Item result = null;
        for (int i = 0; i < position; i++) {
            if (name.equals(items[i].getName())) {
                result = items[i];
            }
        }
        return result;
    }

    /**
    * Вывод всех существующих заявок
    * @return с
    */
    public Item[] getAll() {
        return Arrays.copyOf(items, position);
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        Random rnd = new Random();
        return String.valueOf(rnd.nextInt(100000) + System.currentTimeMillis());
    }

    /**
    * Получение позиции заявки
    * @param id заявка
    * @return индекс заявки
    */
    public int getItemIndex(String id) {
        int index = -1;
        for (int i = 0; i < position; i++) {
            if (id.equals(items[i].getId())) {
                index = i;
            }
        }
        return index;
    }
}
