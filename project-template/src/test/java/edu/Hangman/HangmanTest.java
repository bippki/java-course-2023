package edu.Hangman;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;


import static org.junit.jupiter.api.Assertions.*;

class HangmanGameTest {

    private HangmanGame game;
    private File configFile;


    @BeforeEach
    void setUp() {
        configFile = new File("test_config.json");
        GameConfig.createDefaultConfig(configFile);
        try {
            String content = new String(Files.readAllBytes(configFile.toPath()));
            game = new HangmanGame(new JSONObject(content));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddPlayer() {
        Player player1 = new Player("a");
        Player player2 = new Player("b");

        game.addPlayer(player1);
        game.addPlayer(player2);

        assertEquals(2, game.getPlayers().size());
    }

    @Test
    void testAddPlayerWithDuplicateName() {
        Player player1 = new Player("a");
        Player player2 = new Player("b");

        game.addPlayer(player1);
        game.addPlayer(player2);

        assertEquals(2, game.getPlayers().size());
    }

    @Test
    void testGuessLetterWithWrong() {
        Player player = new Player("Alice");
        game.guessLetter('z', player);
        assertEquals(5, game.getAttemptsLeft());
    }

}
