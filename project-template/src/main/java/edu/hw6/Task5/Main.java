package edu.hw6.Task5;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class Main {
    public static void main(String[] args) {
        HackerNews hackerNews = new HackerNews();
        System.out.println(java.util.Arrays.toString(hackerNews.hackerNewsTopStories()));
        String newsTitle = hackerNews.news(37570037);
        System.out.println(newsTitle);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the ID of the news (enter 'exit' to quit): ");
            if (scanner.hasNext("exit")) {
                break;
            }
            if (scanner.hasNextInt()) {
                int newsId = scanner.nextInt();
                String inputTitle = hackerNews.news(newsId);
                System.out.println("News Title: " + inputTitle);
            } else {
                System.out.println("Invalid input. Please enter a valid news ID or type 'exit' to quit.");
                scanner.next();
            }
        }
    }
}
