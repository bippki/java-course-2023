package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {
    public static boolean regular(String password) {
        String regex = ".*[~!@#$%^&*|].*";
        return password.matches(regex);
    }
}
