package edu.hw3.Task3_5;

import java.util.Arrays;
import java.util.Comparator;

public class Sorter {

    public static Task3_5[] parseContacts(String[] names, String order) {
        if (names == null || names.length == 0) {
            return new Task3_5[0];
        }

        Task3_5[] contacts = Arrays.stream(names)
            .map(Task3_5::new)
            .toArray(Task3_5[]::new);

        Comparator<Task3_5> comparator = (c1, c2) -> {
            String name1 = c1.getLastName();
            String name2 = c2.getLastName();

            if (order.equals("ASC")) {
                return name1.compareTo(name2);
            } else if (order.equals("DESC")) {
                return name2.compareTo(name1);
            } else {
                throw new IllegalArgumentException("Wrong argument");
            }
        };
        Arrays.sort(contacts, comparator);

        return contacts;
    }
}
