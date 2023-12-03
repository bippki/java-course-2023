package edu.hw8.Task3;


import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelDictionaryAttack {

    private final String targetHash;

    public ParallelDictionaryAttack(String targetHash) {
        this.targetHash = targetHash;
    }

    public PasswordCrackResult performAttack(String dictionaryDirectoryPath) {
        Set<String> dictionary = loadDictionary(dictionaryDirectoryPath);

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        long startTime = System.currentTimeMillis();

        CompletableFuture<Void>[] futures = dictionary.stream()
                .map(candidate -> CompletableFuture.runAsync(() -> tryPassword(candidate), executorService))
                .toArray(CompletableFuture[]::new);

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures);
        try {
            allOf.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        long endTime = System.currentTimeMillis();
        long timeSpentMillis = endTime - startTime;

        return new PasswordCrackResult(passwordFound, timeSpentMillis);
    }

    public Set<String> loadDictionary(String directoryPath) {
        Set<String> dictionary = new HashSet<>();

        try {
            Files.walk(Paths.get(directoryPath), FileVisitOption.FOLLOW_LINKS)
                    .filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        try {
                            Files.lines(filePath, StandardCharsets.UTF_8).forEach(dictionary::add);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dictionary;
    }

    private boolean passwordFound = false;

    private void tryPassword(String candidate) {
        String hashedAttempt = hashMD5(candidate);

        if (hashedAttempt.equals(targetHash)) {
            passwordFound = true;
            System.out.println("Password found: " + candidate);
        }
    }

    public String hashMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                result.append(String.format("%02x", b));
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
