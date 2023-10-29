package edu.hw3;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Task3_8<T> implements Iterator<T> {

    private Task3_8() {

    }
    private Collection<T> collection;
    private int currentIndex;

    public Task3_8(Collection<T> collection) {
        this.collection = collection;
        this.currentIndex = collection.size() - 1;
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
        if (collection instanceof List) {
            List<T> list = (List<T>) collection;
            return list.get(index);
        } else {
            Iterator<T> iterator = collection.iterator();
            for (int i = 0; i < index; i++) {
                iterator.next();
            }
            return iterator.next();
        }
    }
}
