package edu.LogParser;

import java.util.Arrays;

public class ConsoleTablePrinter {

    public static void printTable(String[] headers, String[][] data) {
        int[] columnWidths = calculateColumnWidths(headers, data);

        printHorizontalLine(columnWidths);

        printRow(headers, columnWidths);

        printHorizontalLine(columnWidths);

        for (String[] row : data) {
            printRow(row, columnWidths);
        }

        printHorizontalLine(columnWidths);
    }

    private static void printRow(String[] row, int[] columnWidths) {
        StringBuilder rowString = new StringBuilder("|");

        for (int i = 0; i < row.length; i++) {
            rowString.append(" ").append(centerAlign(row[i], columnWidths[i])).append(" |");
        }

        System.out.println(rowString);
    }

    private static void printHorizontalLine(int[] columnWidths) {
        StringBuilder line = new StringBuilder("+");

        for (int width : columnWidths) {
            char[] dashLine = new char[width + 2];
            Arrays.fill(dashLine, '-');
            line.append(dashLine).append("+");
        }

        System.out.println(line);
    }

    private static int[] calculateColumnWidths(String[] headers, String[][] data) {
        int numColumns = headers.length;
        int[] columnWidths = new int[numColumns];

        for (int i = 0; i < numColumns; i++) {
            int maxLength = headers[i].length();

            for (String[] row : data) {
                int cellLength = row[i].length();
                if (cellLength > maxLength) {
                    maxLength = cellLength;
                }
            }

            columnWidths[i] = maxLength;
        }

        return columnWidths;
    }

    private static String centerAlign(String text, int width) {
        int padding = Math.max(0, width - text.length());
        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;

        return " ".repeat(leftPadding) + text + " ".repeat(rightPadding);
    }
}
