package edu.HangmanFinal;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public char getGuess() {
        System.out.print("Guess a letter (Ctrl+D to surrender): ");
        String input;
        char guess;
        while (true) {
            try {
                input = scanner.nextLine();
                if (input.length() != 1) {
                    System.out.println("Please enter a single letter.");
                } else {
                    guess = input.charAt(0);
                    if (!Character.isLetter(guess)) {
                        System.out.println("Please enter a valid letter.");
                    } else {
                        return guess;
                    }
                }
            } catch (Exception e) {
                System.out.println("Surrendered!");
                System.exit(0);
            }
        }
    }
}
