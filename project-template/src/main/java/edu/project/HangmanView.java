package edu.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class HangmanView implements HumanPlayer {
    private final Scanner scanner;

    public HangmanView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayEnterPlayers() {
        System.out.print("Enter number of players: ");
    }

    public void displayPlayerEnterName(int index) {
        System.out.print("Enter player " + (index + 1) + " name: ");
    }

    public void displayWelcomeMessage() {
        System.out.println("Welcome to Hangman!");
    }

    public void displayWord(String word) {
        System.out.println("Word: " + word);
    }

    public void displayAttemptsLeft(int attempts) {
        System.out.println("Attempts remaining: " + attempts);
    }

    public void displayGameOver(List<Player> players) {
        int maxScore = Integer.MIN_VALUE;
        List<Player> winners = new ArrayList<>();

        for (Player player : players) {
            displayPlayerScore(player);
            if (player.getScore() > maxScore) {
                maxScore = player.getScore();
                winners.clear();
                winners.add(player);
            } else if (player.getScore() == maxScore) {
                winners.add(player);
            }
        }

        if (winners.size() == 1) {
            System.out.println("Congratulations! Player " + winners.get(0).getName() + " wins!");
        } else {
            System.out.println("It's a tie between the following players:");
            for (Player player : winners) {
                System.out.println(player.getName());
            }
        }
    }

    public void displayPlayerScore(Player player) {
        System.out.println("Player: " + player.getName() + ", Score: " + player.getScore());
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
