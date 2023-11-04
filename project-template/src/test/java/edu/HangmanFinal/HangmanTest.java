package edu.HangmanFinal;

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
            HumanPlayer playerMain = new HumanPlayer("a");
            game = new HangmanGame(new JSONObject(content),playerMain);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddPlayer() {
        HumanPlayer player1 = new HumanPlayer("a");
        HumanPlayer player2 = new HumanPlayer("b");

        game.addPlayer(player1);
        game.addPlayer(player2);

        assertEquals(2, game.getPlayers().size());
    }

    @Test
    void testAddPlayerWithDuplicateName() {
        HumanPlayer player1 = new HumanPlayer("a");
        HumanPlayer player2 = new HumanPlayer("b");

        game.addPlayer(player1);
        game.addPlayer(player2);

        assertEquals(2, game.getPlayers().size());
    }

    @Test
    void testGuessLetterWithWrong() {
        HumanPlayer player = new HumanPlayer("Alice");
        game.guessLetter('z', player);
        assertEquals(5, game.getAttemptsLeft());
    }

}
