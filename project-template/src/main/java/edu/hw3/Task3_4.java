package edu.hw3;

import java.util.HashMap;
import java.util.Map;

public class Task3_4 {
    private Task3_4() {

    }
    private static final Map<Integer, String> romanDict = new HashMap<>();
    static {
        romanDict.put(1, "I");
        romanDict.put(4, "IV");
        romanDict.put(5, "V");
        romanDict.put(9, "IX");
        romanDict.put(10, "X");
        romanDict.put(40, "XL");
        romanDict.put(50, "L");
        romanDict.put(90, "XC");
        romanDict.put(100, "C");
        romanDict.put(400, "CD");
        romanDict.put(500, "D");
        romanDict.put(900, "CM");
        romanDict.put(1000, "M");
    }
    static final int MIN = 1;
    static final int MAX = 3999;
    public static String convertToRoman(int num) {
        if (num < MIN || num > MAX) {
            throw new IllegalArgumentException("Number must be between 1 and 3999");
        }
        StringBuilder digit = new StringBuilder();
        int[] values = romanDict.keySet().stream()
            .sorted((a, b) -> b - a)
            .mapToInt(Integer::intValue)
            .toArray();

        for (int value : values) {
            while (num >= value) {
                digit.append(romanDict.get(value));
                num -= value;
            }
        }

        return digit.toString();
    }
}
