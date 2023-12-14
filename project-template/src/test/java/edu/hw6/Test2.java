package edu.hw6;

import edu.hw6.Task2.FileCloner;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test2 {

    private static final String FILE_NAME = "disk.txt";
    private static final String FILE_NAME2 = "disk.txt - копия";
    private static final String FILE_NAME3 = "disk.txt- копия (2)";

    /*
    * Та же беда, для путей project-template/src/test/java/edu/hw6/disk.txt
    * template/src/test/java/edu/hw6/disk.txt - копия
    * template/src/test/java/edu/hw6/disk.txt- копия (2)
    * Получается шиш
    * Поменяй пожалуйста на свои AbsolutePath
    * */

    private static final Path FILE_PATH = Paths.get("C:\\Users\\LubluKotov\\IdeaProjects\\java-course-2023\\project-template\\src\\test\\java\\edu\\hw6\\disk.txt");
    private static final Path FILE_PATH2 = Paths.get("C:\\Users\\LubluKotov\\IdeaProjects\\java-course-2023\\project-template\\src\\test\\java\\edu\\hw6\\disk.txt - копия");

    private static final Path FILE_PATH3 = Paths.get("C:\\Users\\LubluKotov\\IdeaProjects\\java-course-2023\\project-template\\src\\test\\java\\edu\\hw6\\disk.txt- копия (2)");


    @AfterEach
    void tearDown() {
        deleteFile(FILE_PATH2.toFile());
    }

    @Test
    void testCloneFile() {
        // Вызываем метод cloneFile
        FileCloner.cloneFile(FILE_PATH);

        // Проверяем, что файл был создан
        assertTrue(fileExists(FILE_PATH2));

        // Проверяем, что имя файла соответствует ожидаемому
        assertEquals(FILE_NAME2, FILE_PATH2.getFileName().toString());

        FileCloner.cloneFile(FILE_PATH);
        assertTrue(fileExists(FILE_PATH3));
        assertEquals(FILE_NAME3, FILE_PATH3.getFileName().toString());

        // Можно добавить дополнительные проверки по содержимому файла, если необходимо

        // Удаляем созданный файл
        deleteFile(FILE_PATH2.toFile());
        deleteFile(FILE_PATH3.toFile());

    }

    private void deleteFile(File file) {
        if (file.exists()) {
            file.delete();
        }
    }

    private boolean fileExists(Path filePath) {
        return filePath.toFile().exists();
    }
}
