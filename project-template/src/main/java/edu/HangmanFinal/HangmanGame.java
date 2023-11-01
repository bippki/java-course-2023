package edu.HangmanFinal;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;


class HangmanGame {
    private final String word;
    private final char[] guessedLetters;
    private int attemptsLeft;
    private final List<Player> players;
    private final HangmanView view;
    private final Set<String> playerNames;

    public HangmanGame(JSONObject config) {
        int maxAttempts = config.getInt("maxAttempts");
        JSONArray wordsArray = config.getJSONArray("words");
        String[] words = new String[wordsArray.length()];
        for (int i = 0; i < wordsArray.length(); i++) {
            words[i] = wordsArray.getString(i);
        }
        this.word = getRandomWord(words);
        this.guessedLetters = new char[word.length()];
        this.attemptsLeft = maxAttempts;
        this.players = new ArrayList<>();
        this.view = new HangmanView();
        this.playerNames = new HashSet<>();
    }

    public void addPlayer(Player player) {
        if (!playerNames.contains(player.getName())) {
            players.add(player);
            playerNames.add(player.getName());
        } else {
            System.out.println("Current name already exist.");
        }
    }

    public void startGame() {
        view.displayWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        view.displayEnterPlayers();

        int numPlayers;
        while (true) {
            try {
                numPlayers = Integer.parseInt(scanner.nextLine());
                if (numPlayers > 0 && numPlayers <5) {
                    break;
                } else {
                    System.out.println("Enter a number greater than zero!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter must be digit!");
            }
        }

        for (int i = 0; i < numPlayers; i++) {
            view.displayPlayerEnterName(i);
            String playerName;
            while (true) {
                playerName = scanner.nextLine();
                if (!playerNames.contains(playerName)) {
                    addPlayer(new Player(playerName));
                    break;
                } else {
                    System.out.println("Current name already exist");
                }
            }
        }

        while (!isGameOver()) {
            for (Player p : players) {
                view.displayWord(getGuessedWord());
                view.displayAttemptsLeft(getAttemptsLeft());
                view.displayPlayerScore(p);
                char guess = view.getGuess();
                guessLetter(guess, p);
                if (isWordGuessed()) {
                    break;
                }
            }
        }

        for (Player p : players) {
            if (isGameWon()) {
                p.updateScore(1);
            }
        }

        view.displayGameOver(players);
    }

    private boolean isWordGuessed() {
        return Arrays.equals(guessedLetters, word.toCharArray());
    }

    private String getRandomWord(String[] words) {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    public boolean isGameOver() {
        return isGameLost() || isGameWon();
    }

    public boolean isGameWon() {
        for (char c : guessedLetters) {
            if (c == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isGameLost() {
        return attemptsLeft <= 0;
    }

    public void guessLetter(char letter, Player player) {
        boolean guessed = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                guessedLetters[i] = letter;
                guessed = true;
            }
        }
        if (!guessed) {
            attemptsLeft--;
            player.updateScore(-1);
        } else {
            player.updateScore(3);
        }

    }
    public String getGuessedWord() {
        return new String(guessedLetters);
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
