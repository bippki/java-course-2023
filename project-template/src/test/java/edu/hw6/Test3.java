package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task3.AbstractFilter.globMatches;
import static edu.hw6.Task3.AbstractFilter.largerThan;
import static edu.hw6.Task3.AbstractFilter.magicNumber;
import static edu.hw6.Task3.AbstractFilter.regexContains;
import static edu.hw6.Task3.Main.readable;
import static edu.hw6.Task3.Main.regularFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test3 {

    @Test
    void FindSquirel() {
        Path dir = Paths.get("project-template/src/test/java/edu/hw6");

        DirectoryStream.Filter<Path> filter = regularFile
            .and(readable)
            .and(largerThan(100))
            .and(magicNumber(0x89, 'P', 'N', 'G'))
            .and(globMatches("*.png"))
            .and(regexContains("[-]"));
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(System.out::println);
            System.setOut(System.out);
            String output = entries.toString().trim();
            assertEquals(output,"project-template/src/test/java/edu/hw6/1-1.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
