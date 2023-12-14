package edu.hw6.Task1;

import java.io.*;
import java.util.*;

public class DiskMap implements Map<String, String> {

    private final File storageFile;

    public DiskMap(String filePath) {
        this.storageFile = new File(filePath);
    }

    @Override
    public int size() {
        return getAllPairs().size();
    }

    @Override
    public boolean isEmpty() {
        return getAllPairs().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return get((String) key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return getAllPairs().containsValue(value);
    }

    @Override
    public String get(Object key) {
        return getAllPairs().get(key);
    }

    @Override
    public String put(String key, String value) {
        Map<String, String> allPairs = getAllPairs();
        String previousValue = allPairs.put(key, value);
        writeToFile(allPairs);
        return previousValue;
    }

    @Override
    public String remove(Object key) {
        Map<String, String> allPairs = getAllPairs();
        String removedValue = allPairs.remove(key);
        writeToFile(allPairs);
        return removedValue;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> map) {
        Map<String, String> allPairs = getAllPairs();
        allPairs.putAll(map);
        writeToFile(allPairs);
    }

    @Override
    public void clear() {
        try {
            new FileOutputStream(storageFile).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<String> keySet() {
        return getAllPairs().keySet();
    }

    @Override
    public Collection<String> values() {
        return getAllPairs().values();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return getAllPairs().entrySet();
    }

    private Map<String, String> getAllPairs() {
        Map<String, String> pairs = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(storageFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] pair = line.split(":");
                    if (pair.length == 2) {
                        pairs.put(pair[0], pair[1]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pairs;
    }

    private void writeToFile(Map<String, String> allPairs) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(storageFile))) {
            for (Entry<String, String> entry : allPairs.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
