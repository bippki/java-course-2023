package edu.hw3;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Task3_3 {
    private Task3_3() {

    }
    public static <T> Map<T, Integer> freqDict(List<T> items) {
        Map<T, Integer> frequencyMap = new HashMap<>();

        for (T item : items) {
            frequencyMap.put(item, frequencyMap.getOrDefault(item, 0) + 1);
        }

        return frequencyMap;
    }
}
