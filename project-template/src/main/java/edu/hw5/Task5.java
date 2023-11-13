package edu.hw5;

public class Task5 {
    public static boolean regexAutoNumber(String password) {
        String regex = "^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{3}$";
        return password.matches(regex);
    }
}
