package edu.hw7.Task3;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PersonDatabase personDatabase = new CachingPersonDatabase();

        Person person1 = new Person(1, "Steve Whois", "Pushkin Street, Kolotushkin 54", "1234-532");
        Person person2 = new Person(2, "Homeless boi", null, "8800-555");

        personDatabase.add(person1);
        personDatabase.add(person2);

        List<Person> johns = personDatabase.findByName("Homeless boi");
        System.out.println("Persons with name John Doe: " + johns);

        List<Person> janeAddress = personDatabase.findByAddress("Pushkin Street, Kolotushkin 54");
        System.out.println("Persons with address 456 Oak St: " + janeAddress);

        List<Person> johnPhone = personDatabase.findByPhone("8800-555");
        System.out.println("Persons with phone number 555-1234: " + johnPhone);
    }
}
