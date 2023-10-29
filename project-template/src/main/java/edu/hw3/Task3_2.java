package edu.hw3;
import java.util.ArrayList;
import java.util.List;

public class Task3_2 {

    private Task3_2() {

    }
    public static List<String> clusterize(String input) {
        List<String> output = new ArrayList<>();
        int right = 0;
        int left = 0;
        int start = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                left++;
            } else if (input.charAt(i) == ')') {
                if (left == right) {
                    throw new IllegalArgumentException("String contains to many ')'!");
                }
                right++;
            }
            if (left == right) {
                output.add(input.substring(start, i + 1));
                start = i + 1;
            }
        }
        if (left > right) {
            throw new IllegalArgumentException("String contains to many '('!");
        }
        return output;
    }
    public static void main(String[] args) {
        System.out.println(clusterize("()()()")); // ["()", "()", "()"]
        System.out.println(clusterize("((()))")); // ["((()))"]
        System.out.println(clusterize("((()))(())()()(()())")); // ["((()))", "(())", "()", "()", "(()())"]
        System.out.println(clusterize("((())())(()(()()))")); // ["((())())", "(()(()()))"]
    }
}
