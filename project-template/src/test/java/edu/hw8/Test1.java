package edu.hw8;


import edu.hw8.Task1.QuoteServer;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test1 {

    private QuoteServer server;

    @Before
    public void setUp() {
        server = new QuoteServer();
        new Thread(() -> server.startServer()).start();
    }

    @After
    public void tearDown() {
        server.stopServer();
    }

    @Test
    public void testPersonQuote() throws IOException, InterruptedException {
        assertQuote("personalities", "Не переходи на личности там, где их нет");
    }

    @Test
    public void testInsultsQuote() throws IOException, InterruptedException {
        assertQuote("insults", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами");
    }

    @Test
    public void testStupidQuote() throws IOException, InterruptedException {
        assertQuote("stupid", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.");
    }

    @Test
    public void testIntelQuote() throws IOException, InterruptedException {
        assertQuote("intelligence", "Чем ниже интеллект, тем громче оскорбления");
    }

    @Test
    public void testDefaultQuote() throws IOException, InterruptedException {
        assertQuote("unknown", "Ну типо");
    }

    private void assertQuote(String keyword, String expectedQuote) throws IOException, InterruptedException {
        Thread clientThread = new Thread(() -> {
            try (Socket socket = new Socket("localhost", 8080)) {
                OutputStream output = socket.getOutputStream();
                output.write(keyword.getBytes());

                InputStream input = socket.getInputStream();
                byte[] buffer = new byte[1024];
                int bytesRead = input.read(buffer);
                String response = new String(buffer, 0, bytesRead);

                assertEquals(expectedQuote, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread.sleep(1000);
        clientThread.start();
        clientThread.join();
    }
}
