package edu.hw5;

import java.util.regex.Pattern;

public class Task8 {

    public static boolean isOddLen(String input) {
        return input.matches("^(.{2})*.$");
    }


    public static boolean zeroCountThree(String input) {
        return input.matches("1*|(1*01*01*01*)*");
    }

    public static boolean exceptOneOne(String input) {
        return input.matches("^(?!11$|111$)[01]+$");
    }


    public static boolean oneStickMoreEyes(String input) {
        return input.matches("^(?=(?:[^1]*1){0,1}[^1]*$)(?=(?:[^0]*0){2,}[^0]*$)[01]*$");
    }


    public static boolean noSticks(String input) {
        return input.matches("^(?!.*11)[01]+$");
    }
}
