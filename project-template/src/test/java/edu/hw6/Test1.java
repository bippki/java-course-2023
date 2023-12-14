package edu.hw6;

import edu.hw6.Task1.DiskMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Test1 {

    private DiskMap diskMap;
    private String filePath;

    @BeforeEach
    void setUp() {

        /*
        * Незнаю почему, но для пути C:\Users\LubluKotov\IdeaProjects\java-course-2023\project-template\src\test\java\edu\hw6\disk.txt он работает, а для
        * пути project-template/src/test/java/edu/hw6/disk.txt уже нет. Кажется что-то с правами доступа.
        * */

        filePath = "project-template/disk.txt";
        diskMap = new DiskMap(filePath);
    }



    @Test
    void testPutAndGet() {
        diskMap.put("key1", "value1");
        assertEquals("value1", diskMap.get("key1"));
    }

    @Test
    void testRemove() {
        diskMap.put("key1", "value1");
        diskMap.remove("key1");
        assertNull(diskMap.get("key1"));
    }

    @Test
    void testSize() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        assertEquals(2, diskMap.size());
    }

    @Test
    void testClear() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        diskMap.clear();
        assertEquals(0, diskMap.size());
        assertTrue(diskMap.isEmpty());
    }

    @Test
    void testContainsKey() {
        diskMap.put("key1", "value1");
        assertTrue(diskMap.containsKey("key1"));
        assertFalse(diskMap.containsKey("nonExistentKey"));
    }

    @Test
    void testContainsValue() {
        diskMap.put("key1", "value1");
        assertTrue(diskMap.containsValue("value1"));
        assertFalse(diskMap.containsValue("nonExistentValue"));
    }

    @Test
    void testKeySet() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        assertTrue(diskMap.keySet().contains("key1"));
        assertTrue(diskMap.keySet().contains("key2"));
    }

    @Test
    void testValues() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        assertTrue(diskMap.values().contains("value1"));
        assertTrue(diskMap.values().contains("value2"));
    }

    @Test
    void testEntrySet() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        assertTrue(diskMap.entrySet().stream().anyMatch(entry -> entry.getKey().equals("key1") && entry.getValue().equals("value1")));
        assertTrue(diskMap.entrySet().stream().anyMatch(entry -> entry.getKey().equals("key2") && entry.getValue().equals("value2")));
    }
}
