package ru.job4j.generic;

import java.util.Iterator;

public class AbstractStore<T extends Base> implements Store<T> {

    private SimpleArray<T> sa = new SimpleArray<>(50);

    @Override
    public void add(T model) {
        sa.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = true;
        int userIdx = indexOf(id);
        if (userIdx != -1) {
            sa.set(userIdx, model);
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = true;
        int userIdx = indexOf(id);
        if (userIdx != -1) {
            sa.remove(userIdx);
        } else {
            result = false;
        }
        return result;
    }


    public int indexOf(String id) {
        Iterator<T> it = sa.iterator();
        int index = 0;
        boolean success = false;
        while (it.hasNext()) {
            if (it.next().getId().equals(id)) {
                success = true;
                break;
            }
            index++;
        }
        return success ? index : -1;
    }

    @Override
    public T findById(String id) {
        Iterator<T> it = sa.iterator();
        T result = null;
        while (it.hasNext()) {
            T base = it.next();
            if (base.getId().equals(id)) {
                result = base;
            }
        }
        return result;
    }
}
