package edu.hw6.Task4;

import edu.hw6.Task2.FileCloner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class Main {
    public static void main(String[] args) {
        Path filePath = Paths.get("src/main/java/edu/hw6/Task4/output");

        try (OutputStream fileOutputStream = Files.newOutputStream(filePath);
             CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new Adler32());
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
             PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {

            printWriter.write("Programming is learned by writing programs. ― Brian Kernighan");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
