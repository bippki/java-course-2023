package edu.hw3;

public class Task3_1 {

    private Task3_1() {
    }
    public static String atbash(String text) {
        StringBuilder result = new StringBuilder();

        for (char symb : text.toCharArray()) {
            if (symb >= 'A' && symb <= 'Z') {
                char reverse = (char) ('Z' - (symb - 'A'));
                result.append(reverse);
            } else if (symb >= 'a' && symb <= 'z') {
                char reverse = (char) ('z' - (symb - 'a'));
                result.append(reverse);
            } else {
                result.append(symb);
            }
        }
        return result.toString();
    }
}
