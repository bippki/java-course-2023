package edu.hw6.Task2;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        /*У меня какая-то проблема с директориями*/
        Path filePath = Paths.get("src/main/java/edu/hw6/Task2/clone.txt");
        FileCloner.cloneFile(filePath);
    }
}
