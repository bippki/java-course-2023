package edu.hw8;

import edu.hw8.Task3.ParallelDictionaryAttack;
import edu.hw8.Task3.PasswordCrackResult;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class ParallelDictionaryAttackTest {

    @Test
    void testPerformAttack() {
        String[] targetHashes = {
            "81dc9bdb52d04dc20036dbd8313ed055",
            "47bce5c74f589f4867dbd57e9ca9f808"
        };
        String dictionaryDirectoryPath = "src/main/resources/dict";

        for (String targetHash : targetHashes) {
            ParallelDictionaryAttack passwordCracker = new ParallelDictionaryAttack(targetHash);
            PasswordCrackResult result = passwordCracker.performAttack(dictionaryDirectoryPath);
            assertTrue(result.passwordFound());
        }
    }

    @Test
    void testNotInDict() {
        String[] targetHashes = {
            "0b89140154e2cfd97c078d050b931e38"

        };
        String dictionaryDirectoryPath = "src/main/resources/dict";

        for (String targetHash : targetHashes) {
            ParallelDictionaryAttack passwordCracker = new ParallelDictionaryAttack(targetHash);
            PasswordCrackResult result = passwordCracker.performAttack(dictionaryDirectoryPath);
            assertFalse(result.passwordFound());
        }
    }


    @Test
    void testLoadDictionary() {
        String directoryPath = "src/main/resources/dict";
        ParallelDictionaryAttack passwordCracker = new ParallelDictionaryAttack("MD5");
        Set<String> dictionary = passwordCracker.loadDictionary(directoryPath);
        assertNotNull(dictionary);
        assertFalse(dictionary.isEmpty());
    }

    @Test
    void testHashMD5() {
        String input = "scidalo";
        ParallelDictionaryAttack passwordCracker = new ParallelDictionaryAttack("MD5");
        String hashedResult = passwordCracker.hashMD5(input);
        assertNotNull(hashedResult);
    }
}
