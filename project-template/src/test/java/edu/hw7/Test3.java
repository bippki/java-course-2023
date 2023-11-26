package edu.hw7;


import edu.hw7.Task3.CachingPersonDatabase;
import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDatabase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class Test3{

    @Test
    public void testName() {
        PersonDatabase personDatabase = new CachingPersonDatabase();

        Person person = new Person(1, "Walter White", "3828 Piermont Dr NE Albuquerque, New Mexico", "1234-567");
        personDatabase.add(person);

        List<Person> result = personDatabase.findByName("Walter White");

        assertEquals(1, result.size());
        assertEquals(person, result.get(0));
    }

    @Test
    public void testAddress() {
        PersonDatabase personDatabase = new CachingPersonDatabase();

        Person person = new Person(1, "Walter White", "3828 Piermont Dr NE Albuquerque, New Mexico", "1234-567");
        personDatabase.add(person);

        List<Person> result = personDatabase.findByAddress("3828 Piermont Dr NE Albuquerque, New Mexico");

        assertEquals(1, result.size());
        assertEquals(person, result.get(0));
    }

    @Test
    public void testPhone() {
        PersonDatabase personDatabase = new CachingPersonDatabase();

        Person person = new Person(1, "Walter White", "3828 Piermont Dr NE Albuquerque, New Mexico", "1234-567");
        personDatabase.add(person);

        List<Person> result = personDatabase.findByPhone("1234-567");

        assertEquals(1, result.size());
        assertEquals(person, result.get(0));
    }

    @Test
    public void testFindAll() {
        PersonDatabase personDatabase = new CachingPersonDatabase();

        Person person = new Person(1, "Walter White", "3828 Piermont Dr NE Albuquerque, New Mexico", "1234-567");
        personDatabase.add(person);
        List<Person> byNameResult = personDatabase.findByName("Walter White");
        List<Person> byAddressResult = personDatabase.findByAddress("3828 Piermont Dr NE Albuquerque, New Mexico");
        List<Person> byPhoneResult = personDatabase.findByPhone("1234-567");

        assertEquals(1, byNameResult.size());
        assertEquals(1, byAddressResult.size());
        assertEquals(1, byPhoneResult.size());

        assertEquals(person, byNameResult.get(0));
        assertEquals(person, byAddressResult.get(0));
        assertEquals(person, byPhoneResult.get(0));
    }

    @Test
    public void testDelete() {
        PersonDatabase personDatabase = new CachingPersonDatabase();

        Person person = new Person(1, "Walter White", "3828 Piermont Dr NE Albuquerque, New Mexico", "1234-567");
        personDatabase.add(person);

        personDatabase.delete(1);

        List<Person> result = personDatabase.findByName("Walter White");

        assertEquals(0, result.size());
    }

    @Test
    public void testFindWhenAttributesAreMissing() {
        PersonDatabase personDatabase = new CachingPersonDatabase();
        Person person = new Person(1, "Gustavo Fring", "Chilli", null);
        personDatabase.add(person);
        assertEquals(0, personDatabase.findByName("Gustavo Fring").size());
        assertEquals(0, personDatabase.findByAddress("Chilli").size());

    }
}
