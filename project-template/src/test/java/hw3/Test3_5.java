package hw3;

import edu.hw3.Task3_5.Sorter;
import edu.hw3.Task3_5.Task3_5;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Test3_5 {

    @Test
    void testASC() {
        String[] names = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        Task3_5[] sortedContacts = Sorter.parseContacts(names, "ASC");
        assertNotNull(sortedContacts);
        assertEquals("Thomas Aquinas", sortedContacts[0].getFullName());
        assertEquals("Rene Descartes", sortedContacts[1].getFullName());
        assertEquals("David Hume", sortedContacts[2].getFullName());
        assertEquals("John Locke", sortedContacts[3].getFullName());
    }

    @Test
    void testDESC() {
        String[] names = {"Carl Gauss", "Leonhard Euler", "Paul Erdos"};
        Task3_5[] sortedContacts = Sorter.parseContacts(names, "DESC");
        assertNotNull(sortedContacts);
        assertEquals("Carl Gauss", sortedContacts[0].getFullName());
        assertEquals("Leonhard Euler", sortedContacts[1].getFullName());
        assertEquals("Paul Erdos", sortedContacts[2].getFullName());
    }

    @Test
    void testEmpty() {
        String[] names = {};
        Task3_5[] sortedContacts = Sorter.parseContacts(names, "ASC");
        assertNotNull(sortedContacts);
        assertEquals(0, sortedContacts.length);
    }

    @Test
    void testNull() {
        String[] names = null;
        Task3_5[] sortedContacts = Sorter.parseContacts(names, "ASC");
        assertNotNull(sortedContacts);
        assertEquals(0, sortedContacts.length);
    }
}
