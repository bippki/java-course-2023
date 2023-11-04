package edu.HangmanFinal;

import java.util.ArrayList;
import java.util.List;

class HangmanView implements Player {
    private final InputHandler inputHandler;

    public HangmanView() {
        this.inputHandler = new InputHandler();
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

    public void displayGameOver(List<HumanPlayer> players) {
        int maxScore = Integer.MIN_VALUE;
        List<HumanPlayer> winners = new ArrayList<>();

        for (HumanPlayer player : players) {
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
            for (HumanPlayer player : winners) {
                System.out.println(player.getName());
            }
        }
    }

    public void displayPlayerScore(HumanPlayer player) {
        System.out.println("Player: " + player.getName() + ", Score: " + player.getScore());
    }

    public char getGuess() {
        return inputHandler.getGuess();
    }
}
