package edu.hw3;

import java.util.Comparator;

public class Task3_7<T> implements Comparator<T> {

    private final Comparator<T> comparator;

    public Task3_7(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int compare(T o1, T o2) {
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 == null) {
            return 1;
        } else if (o2 == null) {
            return -1;
        } else {
            return comparator.compare(o1, o2);
        }
    }
}
