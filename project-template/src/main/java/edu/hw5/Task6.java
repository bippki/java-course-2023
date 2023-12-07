package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {
    public static boolean findSubstring(String s, String t) {
        StringBuilder patternBuilder = new StringBuilder();
        for (char c : s.toCharArray()) {
            patternBuilder.append(".*").append(c);
        }
        patternBuilder.append(".*");

        String regex = patternBuilder.toString();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(t);

        return matcher.matches();
    }
}
