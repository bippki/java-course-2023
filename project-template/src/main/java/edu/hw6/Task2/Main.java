package edu.hw6.Task2;

import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {
        Path filePath = Paths.get("project-template/src/main/java/edu/hw6/Task2/clone");

        FileCloner.cloneFile(filePath);
    }
}

