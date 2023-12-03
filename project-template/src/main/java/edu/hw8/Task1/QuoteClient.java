package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class QuoteClient {
    private QuoteClient(){};
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Vanya: ");
                String keyword = scanner.nextLine();

                try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT)) {
                    OutputStream output = socket.getOutputStream();
                    output.write(keyword.getBytes());

                    InputStream input = socket.getInputStream();
                    byte[] buffer = new byte[1024];
                    int bytesRead = input.read(buffer);
                    if (bytesRead == -1) {
                        throw new IOException("Error reading from the server.");
                    }
                    String response = new String(buffer, 0, bytesRead);

                    System.out.println("Server: " + response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
