package edu.hw3;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class Task3_8<T> implements Iterator<T> {
    private List<T> list;
    private int currentIndex;

    public Task3_8(Collection<T> collection) {
        this.list = new ArrayList<>(collection);
        this.currentIndex = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return currentIndex >= 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Collection is empty");
        }
        return getValue(currentIndex--);
    }

    private T getValue(int index) {
        return list.get(index);
    }
}
