package ru.job4j.io.bot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestInput implements Input {
    private List<String> inputs;
    private Iterator<String> iter;

    public TestInput(List<String> inputs) {
        this.inputs = inputs;
        this.iter = this.inputs.iterator();
    }

    @Override
    public String getInput() {
        return iter.hasNext() ? iter.next() : "exit";
    }

}
