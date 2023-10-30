package edu.hw3.Task3_5;

import java.util.Arrays;
import java.util.Comparator;

public class Sorter {

    public static Person[] parseContacts(String[] names, String order) {
        if (names == null || names.length == 0) {
            return new Person[0];
        }
        Person[] contacts = Arrays.stream(names)
            .map(Person::new)
            .toArray(Person[]::new);

        Comparator<Person> comparator = Comparator.comparing(Person::getLastName)
            .thenComparing(Person::getFirstName);

        if (order.equals("DESC")) {
            comparator = comparator.reversed();
        } else if (!order.equals("ASC")) {
            throw new IllegalArgumentException("Wrong argument");
        }

        Arrays.sort(contacts, comparator);

        return contacts;
    }
}
