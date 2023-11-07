package edu.HangmanFinal;

class HumanPlayer {
    private final String name;
    private int score;

    public HumanPlayer(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void updateScore(int points) {
        score = Math.max(score + points, 0);
    }
}
