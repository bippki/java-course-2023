package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCloner {

    public static void cloneFile(Path path) {
        String fileName = path.getFileName().toString();
        String directory = path.getParent().toString();

        if (Files.exists(path)) {
            int copyNumber = 1;
            Path copyPath = Paths.get(directory, getCopyFileName(fileName, copyNumber));
            while (Files.exists(copyPath)) {
                copyNumber++;
                copyPath = Paths.get(directory, getCopyFileName(fileName, copyNumber));
            }

            try {
                Files.copy(path, copyPath);
                System.out.println("File cloned successfully: " + copyPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not exist.");
        }
    }

    private static String getCopyFileName(String fileName, int copyNumber) {
        if (copyNumber == 1) {
            return fileName + " - копия";
        } else {
            return fileName + "- копия (" + copyNumber + ")";
        }
    }
}
