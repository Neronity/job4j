package ru.job4j.tracker;

import java.util.List;

public class StubInput implements Input {
	
    private final List<String> value;

    private int position;

    public StubInput(final List<String> value) {
        this.value = value;
    }

    @Override
    public String ask(String question) {
        return this.value.get(this.position++);
    }

    public int ask(String question, List<Integer> range) throws MenuOutOfRangeException {
        int key = Integer.parseInt(ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutOfRangeException();
        }
    }
}