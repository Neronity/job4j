package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker implements ITracker {
    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Указатель ячейки для новой заявки.
     */

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     * @return
     */
    public String add(Item item) {
        String id = this.generateId();
        item.setId(id);
        items.add(item);
        return id;
    }

    /**
     * Замена существующей заявки
     * @param id заявка, которую нужно заменить
     * @param item заявка, на которую заменяем
     */
    public boolean replace(String id, Item item) {
        int idx = getItemIndex(id);
        if (idx != -1) {
            items.set(idx, item);
            return true;
        }
        return false;
    }

    /**
     * Удаление существующей заявки;
     * @param id заявка, которую нужно удалить;
     */
    public boolean delete(String id) {
        int index = getItemIndex(id);
        if (index != -1) {
            items.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Поиск заявки по ID
     * @param id
     * @return item
     */
    public Item findById(String id) {
        Item result = null;
        for (Item i : items) {
            if (i.getId().equals(id)) {
                result = i;
            }
        }
        return result;
    }

    /**
     * Поиск заявки по имени
     * @param name имя заявки
     * @return заявка с указанным именем
     */
    public List<Item> findByName(String name) {
        List<Item> result = new ArrayList<>();
        for (Item i : items) {
            if (name.equals(i.getName())) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * Вывод всех существующих заявок
     * @return с
     */
    public List<Item> getAll() {
        return this.items;
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
        int index = 0;
        for (Item i : items) {
            if (id.equals(i.getId())) {
                return index;
            }
            index++;
        }
        return -1;
    }
}