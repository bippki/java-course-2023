package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
                InputStream input = clientSocket.getInputStream();
                OutputStream output = clientSocket.getOutputStream()
        ) {
            byte[] buffer = new byte[1024];
            int bytesRead = input.read(buffer);
            if (bytesRead == -1) {
                throw new IOException("Error reading from the client.");
            }
            String keyword = new String(buffer, 0, bytesRead).trim();

            String response = getQuote(keyword);

            output.write(response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getQuote(String keyword) {
        // Add your logic to generate quotes based on keywords
        return switch (keyword.toLowerCase()) {
            case "personalities" -> "Не переходи на личности там, где их нет";
            case "insults" ->
                    "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами";
            case "stupid" ->
                    "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.";
            case "intelligence" -> "Чем ниже интеллект, тем громче оскорбления";
            default -> "Ну типо";
        };
    }
}
