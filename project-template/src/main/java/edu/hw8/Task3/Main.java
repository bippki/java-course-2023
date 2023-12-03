package edu.hw8.Task3;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java ParallelDictionaryAttack <targetHash> <dictionaryFilePath>");
            System.exit(1);
        }

        String targetHash = args[0];
        String dictionaryFilePath = args[1];

        ParallelDictionaryAttack passwordCracker = new ParallelDictionaryAttack(targetHash);
        passwordCracker.performAttack(dictionaryFilePath);
    }
}
