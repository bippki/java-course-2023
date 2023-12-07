package edu.hw5;

import java.util.regex.Pattern;

public class Task7 {

    public static boolean thirdSymbZero(String input) {
        return input.matches(".{2}0.*");
    }

    public static boolean sameStartAndEnd(String input) {
        return input.matches("^(.).*\\1$");
    }

    public static boolean totallySpice(String input) {
        return input.matches(".{1,3}");
    }}
