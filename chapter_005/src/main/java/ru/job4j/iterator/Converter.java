package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> currentIter;

            {
                currentIter = refreshIterator();
            }

            @Override
            public boolean hasNext() {
                if (currentIter != null && !currentIter.hasNext()) {
                    currentIter = refreshIterator();
                }
                return currentIter != null;
            }

            @Override
            public Integer next() {
                if (currentIter != null && !currentIter.hasNext()) {
                    currentIter = refreshIterator();
                }
                if (currentIter == null) {
                    throw new NoSuchElementException();
                }
                return currentIter.next();
            }

            private Iterator<Integer> refreshIterator() {
                Iterator<Integer> iter = null;
                while (it.hasNext()) {
                    iter = it.next();
                    if (iter.hasNext()) {
                        break;
                    }
                }
                if (iter != null && !iter.hasNext()) {
                    iter = null;
                }
                return iter;
            }
        };
    }
}
