package edu.hw8.Task1;

import edu.hw8.Task1.ClientHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuoteServer {
    private static final int PORT = 8080;
    private static final int MAX_CONNECTIONS = 5;

    private QuoteServer(){};

    private ServerSocket serverSocket;
    private ExecutorService executorService;

    public void startServer() {
        executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.submit(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        try {
            serverSocket.close();
            executorService.shutdownNow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
