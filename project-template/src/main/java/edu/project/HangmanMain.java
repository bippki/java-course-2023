package edu.project;

import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HangmanMain {
    public static void main(String[] args) {
        File configFile = new File("config.json");
        if (!configFile.exists()) {
            GameConfig.createDefaultConfig(configFile);
        }

        try {
            String content = new String(Files.readAllBytes(Paths.get("config.json")));
            JSONObject obj = new JSONObject(content);

            HangmanGame game = new HangmanGame(obj);

            game.startGame();

        } catch (Exception e) {
            System.out.println("Error reading configuration: " + e.getMessage());
        }
    }
}
