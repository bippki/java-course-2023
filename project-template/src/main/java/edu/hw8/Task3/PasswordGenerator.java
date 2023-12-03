package edu.hw8.Task3;

import java.io.FileWriter;
import java.io.IOException;

public class PasswordGenerator {

    public static void main(String[] args) {
        generatePasswords();
    }

    //Не все сгенерировал
    public static void generatePasswords() {
        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        try (FileWriter fileWriter1to3 = new FileWriter("passwords_1to3.txt");
             FileWriter fileWriter4 = new FileWriter("passwords_4.txt");
             FileWriter fileWriter5 = new FileWriter("passwords_5.txt")) {

            for (int passwordLength = 1; passwordLength <= 3; passwordLength++) {
                generatePasswordsRecursively(fileWriter1to3, characters, "", passwordLength);
            }

            generatePasswordsRecursively(fileWriter4, characters, "", 4);
            generatePasswordsRecursively(fileWriter5, characters, "", 5);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generatePasswordsRecursively(FileWriter fileWriter, String characters,
        String currentPassword, int length) throws IOException {
        if (length == 0) {
            fileWriter.write(currentPassword + "\n");
            return;
        }

        for (int i = 0; i < characters.length(); i++) {
            generatePasswordsRecursively(fileWriter, characters,
                currentPassword + characters.charAt(i), length - 1);
        }
    }
}
