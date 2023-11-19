package edu.hw6.Task3;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static edu.hw6.Task3.AbstractFilter.globMatches;
import static edu.hw6.Task3.AbstractFilter.largerThan;
import static edu.hw6.Task3.AbstractFilter.magicNumber;
import static edu.hw6.Task3.AbstractFilter.regexContains;


public class Main {
    public static final AbstractFilter regularFile = Files::isRegularFile;
    public static final AbstractFilter readable = Files::isReadable;

    public static void main(String[] args) {
        Path dir = Paths.get("src/main/java/edu/hw6/Task3");

        DirectoryStream.Filter<Path> filter = regularFile
                .and(readable)
                .and(largerThan(100))
                .and(magicNumber(0x89, 'P', 'N', 'G'))
                .and(globMatches("*.png"))
                .and(regexContains("[-]"));
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
