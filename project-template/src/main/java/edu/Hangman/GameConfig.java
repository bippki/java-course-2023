package edu.Hangman;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

record GameConfig(int maxAttempts, String[] words) {

    public static void createDefaultConfig(File configFile) {
        JSONObject config = new JSONObject();
        config.put("maxAttempts", 6);
        JSONArray words = new JSONArray();
        words.put("fork");
        words.put("glasses");
        words.put("flyweight");
        words.put("razor");
        words.put("elephant");
        words.put("grill");
        words.put("camel");
        config.put("words", words);

        try (FileWriter file = new FileWriter(configFile)) {
            file.write(config.toString());
        } catch (IOException e) {
            System.out.println("Error creating default configuration: " + e.getMessage());
        }
    }
}
